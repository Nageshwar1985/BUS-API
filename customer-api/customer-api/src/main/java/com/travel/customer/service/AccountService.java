package com.travel.customer.service;


import java.util.List;

import com.travel.customer.model.CustomerAccount;
import com.travel.customer.model.Login;

public interface AccountService {
	
	public CustomerAccount getAccount(String accountId);
	public List<CustomerAccount> getAccounts();
	public CustomerAccount addAccount(CustomerAccount account);
	public CustomerAccount updateAccount(String customerId,CustomerAccount account);
	public void deleteAccount(String accountId);
	public List<CustomerAccount> accessAccount(Login login) ;

}
