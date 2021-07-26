package com.travel.busgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouchpointDto {

    private int id;
    private String deviceName;
    private String channelName;
    private String password;
}
