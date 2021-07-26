package com.travel.customer.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.customer.exception.ResourceNotFoundException;
import com.travel.customer.model.Customer;
import com.travel.customer.model.CustomerAccount;
import com.travel.customer.model.Login;
import com.travel.customer.repository.CustomerRepository;
import com.travel.customer.repository.LoginRepository;
import com.travel.customer.util.PasswordGenerator;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

	Logger logger = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);

	private CustomerRepository customerRegistrationRepository;
	private LoginRepository loginRepository;

	@Autowired
	CustomerRegistrationServiceImpl(CustomerRepository customerRegistrationRepository,
			LoginRepository loginRepository) {
		this.customerRegistrationRepository = customerRegistrationRepository;
		this.loginRepository = loginRepository;
	}

	public List<Customer> getCustomers() {
		List<Customer> customers = customerRegistrationRepository.findAll();
		logger.info("get all customer details response ", customers);
		return customers;
	}

	public Customer getCustomer(String customerId) {
		Customer customer = customerRegistrationRepository.findById(Long.valueOf(customerId))
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested customer Not found"));
		logger.info("get  customer details response ", customer);
		return customer;
	}
	
	public Login getLogin(Long loginId) {
		Login login = loginRepository.findById(loginId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested Customer Not found"));
		logger.info("get  customer details response {} ", login);
		return login;
	}
	
	public Login getLogin(String loginName) {

		Login login = loginRepository.findByloginName(loginName);
		if (null == login)
			throw new ResourceNotFoundException("002.001", "Requested Customer Not found");
		logger.info("get  customer details response  {} ", login);
		return login;

	}
	
	public Login doLogin(Login userlogin) {
		Login loggedInUser = null;
		
		Login login = loginRepository.findByloginName(userlogin.getLoginName());
		if(null == login)
			throw new ResourceNotFoundException("002.001", "Requested customer Not found");
		if(login!=null && login.equals(userlogin)) {
			login.setLoggedInAt(Timestamp.from(Instant.now()));
			 loggedInUser = loginRepository.save(login);
		}
			else throw new ResourceNotFoundException("002.001", "Requested customer credential not valid");
		
		logger.info(" loggedin customer details response ", loggedInUser);
		
		return loggedInUser;
	}

	public Login addCustomer(Customer customer) {
		Customer createdCustomer = null;
		Login login = new Login(PasswordGenerator.generatePassword(8), "Yes");
		if (null != login) {
			Login createdLogin = loginRepository.save(login);
			createdLogin.setLoginName(createdLogin.getId()+customer.getFirstName());
			customer.setLogin(createdLogin);
			logger.info("saving customer details  ", customer);
			createdCustomer = customerRegistrationRepository.save(customer);
			logger.info("saved customer details response ", createdCustomer);
		}
		
		if(createdCustomer != null) {
			CustomerAccount account = new CustomerAccount("Savings", 0.0, 0.0,"HDFC");
		
		account.setCustomer(createdCustomer);
		}
		return createdCustomer != null ? login : null;
	}

	public Customer updateCustomer(String customerId, Customer customer) {
		Customer existingCustomer = customerRegistrationRepository.findById(Long.valueOf(customerId)).orElse(null);
		logger.info("existing customer details response ", existingCustomer);
		Customer updatedCustomer = null;
		if (null != existingCustomer) {
			customer.setId(existingCustomer.getId());
			customer.setLogin(existingCustomer.getLogin());
			logger.info("update customer details  ", customer);
			updatedCustomer = customerRegistrationRepository.save(customer);
			logger.info("updated customer details response ", updatedCustomer);
		} else
			throw new ResourceNotFoundException("002.001", "Requested customer Not found");
		return updatedCustomer != null ? updatedCustomer : null;
	}

	public void deleteCustomer(String customerId) {
		customerRegistrationRepository.deleteById(Long.valueOf(customerId));
	}

}
