package com.travel.busgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travel.busgateway.model.Touchpoint;
import com.travel.busgateway.model.TouchpointDto;
import com.travel.busgateway.service.TouchpointService;

@RestController
public class RegistrationController {

	@Autowired
	private TouchpointService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Touchpoint signup(@RequestBody TouchpointDto user) {
		return userService.save(user);
	}

}
