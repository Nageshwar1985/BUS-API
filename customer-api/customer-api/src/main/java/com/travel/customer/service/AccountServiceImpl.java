package com.travel.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.customer.exception.ResourceNotFoundException;
import com.travel.customer.model.Customer;
import com.travel.customer.model.CustomerAccount;
import com.travel.customer.model.Login;
import com.travel.customer.repository.AccountRepository;
import com.travel.customer.repository.CustomerRepository;
import com.travel.customer.repository.LoginRepository;

@Service
public class AccountServiceImpl implements AccountService {

	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private LoginRepository loginRepository;

	@Autowired
	AccountServiceImpl(CustomerRepository customerRepository,
			AccountRepository accountRepository,LoginRepository loginRepository) {
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.loginRepository = loginRepository;
	}

	public List<CustomerAccount> getAccounts() {
		List<CustomerAccount> accounts = accountRepository.findAll();
		logger.info("get all account details response ", accounts);
		return accounts;
	}

	public CustomerAccount getAccount(String accountId) {
		CustomerAccount account = accountRepository.findById(Long.valueOf(accountId))
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));
		logger.info("get  account details response ", account);
		return account;
	}

	public CustomerAccount addAccount(CustomerAccount account) {
		CustomerAccount createdAccount = null;
		Customer existingCustomer= customerRepository.findById(account.getCustomer().getId()).orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested customer Not found"));;
		account.setCustomer(existingCustomer);
		logger.info("saving account details  ", account);
		createdAccount = accountRepository.save(account);
		logger.info("saved account details response ", createdAccount);
		return createdAccount != null ? createdAccount : null;
	}
	
	public List<CustomerAccount> accessAccount(Login login) {
		List<CustomerAccount> createdAccounts = null;
		Login exitingLogin = loginRepository.findById(login.getId()).orElseThrow(() -> new ResourceNotFoundException("002.001", "LoginId Not found"));
		logger.info("saved existinglogin details response {}", exitingLogin);
		if (exitingLogin.equals(login)) {
//			Customer customer = customerRepository.findByLogin(exitingLogin);
//			accountRepository.find
			createdAccounts = accountRepository.findByCustomerId(login.getId());
			logger.info("saved account details response {}", createdAccounts);
		}
		return createdAccounts != null ? createdAccounts :   new ArrayList<>();
	}

	public CustomerAccount updateAccount(String accountId, CustomerAccount account) {
		CustomerAccount existingAccount = accountRepository.findById(Long.valueOf(accountId)).orElse(null);
		logger.info("existing account details response ", existingAccount);
		CustomerAccount updatedAccount = null;
		if (null != existingAccount) {
			account.setId(existingAccount.getId());
			account.setCustomer(existingAccount.getCustomer());
			logger.info("update account details  ", account);
			updatedAccount = accountRepository.save(account);
			logger.info("updated account details response ", updatedAccount);
		} else
			throw new ResourceNotFoundException("002.001", "Requested resource Not found");
		return updatedAccount != null ? updatedAccount : null;
	}

	public void deleteAccount(String accountId) {
		accountRepository.deleteById(Long.valueOf(accountId));
	}

}
