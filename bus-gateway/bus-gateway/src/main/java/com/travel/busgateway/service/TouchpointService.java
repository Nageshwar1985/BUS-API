package com.travel.busgateway.service;

import java.util.List;

import com.travel.busgateway.model.Touchpoint;
import com.travel.busgateway.model.TouchpointDto;

public interface TouchpointService {

    Touchpoint save(TouchpointDto user);
    List<Touchpoint> findAll();
    void delete(int id);

    Touchpoint findOne(String username);

    Touchpoint findById(int id);

    TouchpointDto update(TouchpointDto touchPointDto);
}
