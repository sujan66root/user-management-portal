package com.sujan.usermanagementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqResDto {
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String username;
    private String city;
    private String role;
    private String password;
    private int statusCode;
    private String error;
    private String message;

}
