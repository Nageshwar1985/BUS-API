package com.travel.route.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travel.route.model.Bus;
import com.travel.route.repository.BusRepository;


@Component
public class Processor implements ItemProcessor<Bus, Bus> {

	@Autowired
	private BusRepository busRepo;

	@Override
	public Bus process(Bus bus) throws Exception {
		return bus;
	}

}
