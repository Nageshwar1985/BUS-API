package com.travel.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.customer.model.Customer;
import com.travel.customer.model.Login;

public interface LoginRepository  extends JpaRepository<Login,Long> {
	
	 Login	findByloginName(String loginName);

}
