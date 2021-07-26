package com.travel.busgateway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travel.busgateway.model.Touchpoint;

@Repository
public interface TouchpointRepository extends CrudRepository<Touchpoint, Integer> {

    Touchpoint findByChannelName(String channelName);
}
