package com.travel.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.customer.model.CustomerAccount;
import com.travel.customer.model.Login;
import com.travel.customer.service.AccountService;

@RestController
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	private AccountService accountService;

	@Autowired
	AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<CustomerAccount>> getAccounts() {
		logger.info("get all account details  ");
		return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
	}

	@GetMapping("/accounts/{id}")
	public ResponseEntity<CustomerAccount> getAccount(@PathVariable String id) {
		logger.info("get account details for id : {} ", id);
		return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
	}
	
	@PostMapping("/accounts/login")
	public ResponseEntity<List<CustomerAccount>> accessAccount(@Valid @RequestBody Login login) {

		logger.info("access account login details : {} ", login);
		return new ResponseEntity<>(accountService.accessAccount(login), HttpStatus.OK);
	}

	@PostMapping("/accounts/")
	public ResponseEntity<CustomerAccount> addAccount(@Valid @RequestBody CustomerAccount account) {

		logger.info("add account details : {} ", account);
		return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
	}

	@PutMapping("/accounts/{id}")
	public ResponseEntity<CustomerAccount> updateAccount(@PathVariable("id") String id,
			@Valid @RequestBody CustomerAccount account) {
		logger.info("update account details : {} ", account);
		return new ResponseEntity<>(accountService.updateAccount(id, account), HttpStatus.OK);
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") String id) {
		logger.info("delete account details for id : {} ", id);
		accountService.deleteAccount(id);
		return new ResponseEntity<>("Requested CustomerAccount has been deleted", HttpStatus.OK);
	}

}
