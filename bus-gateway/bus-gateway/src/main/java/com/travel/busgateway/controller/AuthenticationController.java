package com.travel.busgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travel.busgateway.config.JwtTokenUtil;
import com.travel.busgateway.model.ApiResponse;
import com.travel.busgateway.model.AuthToken;
import com.travel.busgateway.model.LoginTouchpoint;
import com.travel.busgateway.model.Touchpoint;
import com.travel.busgateway.service.TouchpointService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private TouchpointService touchpointService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ApiResponse<AuthToken> register(@RequestBody LoginTouchpoint loginTouchpoint) throws AuthenticationException {
try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginTouchpoint.getChannelname(), loginTouchpoint.getPassword()));
		}
catch(Exception ex) {
	return new ApiResponse<>(401, "Login Failed due to invalid credentials", new AuthToken(null, loginTouchpoint.getChannelname()));
}
		final Touchpoint touchpoint = touchpointService.findOne(loginTouchpoint.getChannelname());
		final String token = jwtTokenUtil.generateToken(touchpoint);
		return new ApiResponse<>(200, "success", new AuthToken(token, touchpoint.getChannelName()));
	}

}
