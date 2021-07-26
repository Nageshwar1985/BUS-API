package com.travel.busgateway.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.busgateway.model.Touchpoint;
import com.travel.busgateway.model.TouchpointDto;
import com.travel.busgateway.repository.TouchpointRepository;


@Service(value = "touchpointService")
public class TouchpointServiceImpl implements UserDetailsService, TouchpointService {
	
	@Autowired
	private TouchpointRepository touchpointRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Touchpoint user = touchpointRepository.findByChannelName(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getChannelName(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Touchpoint> findAll() {
		List<Touchpoint> list = new ArrayList<>();
		touchpointRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		touchpointRepository.deleteById(id);
	}

	@Override
	public Touchpoint findOne(String username) {
		return touchpointRepository.findByChannelName(username);
	}

	@Override
	public Touchpoint findById(int id) {
		Optional<Touchpoint> optionalUser = touchpointRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public TouchpointDto update(TouchpointDto userDto) {
        Touchpoint user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            touchpointRepository.save(user);
        }
        return userDto;
    }

    @Override
    public Touchpoint save(TouchpointDto user) {
	    Touchpoint newUser = new Touchpoint();
	    newUser.setChannelName(user.getChannelName());
	    newUser.setDeviceName(user.getDeviceName());
//	    newUser.setLastName(user.getLastName());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		newUser.setAge(user.getAge());
//		newUser.setSalary(user.getSalary());
        return touchpointRepository.save(newUser);
    }
}
