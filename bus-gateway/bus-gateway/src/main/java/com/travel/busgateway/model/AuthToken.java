package com.travel.busgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {

    private String token;
    private String channelname;

//    public AuthToken(){
//
//    }
//
//    public AuthToken(String token, String username){
//        this.token = token;
//        this.username = username;
//    }
//
//    public AuthToken(String token){
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}
