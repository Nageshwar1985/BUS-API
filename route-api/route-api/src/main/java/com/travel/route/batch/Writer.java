package com.travel.route.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.travel.route.model.Bus;
import com.travel.route.repository.BusRepository;


@Component
public class Writer implements ItemWriter<Bus>{
	
	@Autowired
	private BusRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Bus> buses) throws Exception {
		repo.saveAll(buses);
	}
	
}
