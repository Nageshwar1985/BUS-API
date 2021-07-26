package com.travel.payment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.payment.client.BookingClient;
import com.travel.payment.dto.BookingDTO;
import com.travel.payment.exception.ResourceNotFoundException;
import com.travel.payment.model.CustomerAccount;
import com.travel.payment.model.Payment;
import com.travel.payment.model.Transfer;
import com.travel.payment.model.VendorAccount;
import com.travel.payment.repository.CustomerAccountRepository;
import com.travel.payment.repository.PaymentRepository;
import com.travel.payment.repository.TransferRepository;
import com.travel.payment.repository.VendorAccountRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	private TransferRepository transferRepository;

	private PaymentRepository paymentRepository;
	private CustomerAccountRepository customerAccountRepository;
	private VendorAccountRepository vendorAccountRepository;

	private BookingClient bookingClient;


	@Autowired
	PaymentServiceImpl(BookingClient bookingClient, PaymentRepository paymentRepository,
			TransferRepository transferRepository, CustomerAccountRepository customerAccountRepository,
			VendorAccountRepository vendorAccountRepository) {
		this.customerAccountRepository = customerAccountRepository;
		this.vendorAccountRepository = vendorAccountRepository;
		this.bookingClient = bookingClient;
		this.paymentRepository = paymentRepository;
		this.transferRepository = transferRepository;
	}

	public List<Transfer> getPaymentTransfers() {
		List<Transfer> transfers = transferRepository.findAll();
		logger.info("get all Payment details response ", transfers);

		return transfers;
	}

	public Transfer getPaymentTransfer(Long paymentId) {
		Transfer transfer = transferRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  Product details response ", transfer);
		return transfer;
	}

	public Transfer placePayment(Long bookingId, Long userId) {

		Transfer updatedTransfer = null;
		BookingDTO bookingDTO = bookingClient.getBooking(bookingId);
		logger.info("get ordertDTO  : {}", bookingDTO);
		CustomerAccount customerAccount = customerAccountRepository.findById(userId).orElse(null);
		logger.info("get customerAccount  : {}", customerAccount);
		VendorAccount vendorAccount = vendorAccountRepository.findById(Long.valueOf(1))
				.orElse(null);
		logger.info("get vendorAccount  : {}", vendorAccount);
		Payment payment = new Payment(bookingDTO.getCartId(), "paid", Timestamp.from(Instant.now()),
				Timestamp.from(Instant.now()), bookingDTO.getTotalAmount());

		Payment updatedPayment = doTransfer(customerAccount, vendorAccount, bookingDTO.getTotalAmount(), payment);
		logger.info("get updatedPayment  : {}", updatedPayment);
		if (updatedPayment != null) {
			Payment latestPayment = paymentRepository.save(updatedPayment);
			Transfer transfer = new Transfer(bookingDTO.getTotalAmount(), "Success");
			transfer.setPayment(latestPayment);
			updatedTransfer = transferRepository.save(transfer);
		}

		else {
			Transfer transfer = new Transfer(bookingDTO.getTotalAmount(), "Failure");
			updatedTransfer = transferRepository.save(transfer);
		}
		logger.info("saved updatedTransfer  : {}", updatedTransfer);

		return updatedTransfer;
	}

	private Payment doTransfer(CustomerAccount customerAccount, VendorAccount vendorAccount, Double orderAmount,
			Payment payment) {
		if (customerAccount.getCurrentBalance() >= orderAmount) {
			Double existingCurrentBalanceForCustomer = customerAccount.getCurrentBalance();
			Double existingCurrentBalanceForVendor = vendorAccount.getCurrentBalance();

			Double updatedCurrentBalanceForCustomer = existingCurrentBalanceForCustomer - orderAmount;
			Double updatedCurrentBalanceForVendor = existingCurrentBalanceForVendor + orderAmount;
			try {
				customerAccount.setCurrentBalance(updatedCurrentBalanceForCustomer);
				CustomerAccount updatedCustomerAccount = customerAccountRepository.save(customerAccount);

				vendorAccount.setCurrentBalance(updatedCurrentBalanceForVendor);
				VendorAccount updatedVendorAccount = vendorAccountRepository.save(vendorAccount);

				if (updatedCurrentBalanceForCustomer == updatedCustomerAccount.getCurrentBalance()
						&& updatedCurrentBalanceForVendor == updatedVendorAccount.getCurrentBalance()) {
					payment.setVendorAccount(vendorAccount);
					payment.setCustomerAccount(customerAccount);

				}

				else
					payment = null;

			} catch (Exception e) {
				customerAccount.setCurrentBalance(existingCurrentBalanceForCustomer);
				customerAccountRepository.save(customerAccount);
				vendorAccount.setCurrentBalance(existingCurrentBalanceForVendor);
				vendorAccountRepository.save(vendorAccount);
				payment = null;
			}

		}

		else
			payment = null;

		return payment;
	}

	public CustomerAccount createCustomerAccount(CustomerAccount customerAccount) {
		return customerAccountRepository.save(customerAccount);
	}

	public VendorAccount createVendorAccount(VendorAccount vendorAccount) {
		return vendorAccountRepository.save(vendorAccount);
	}

}
