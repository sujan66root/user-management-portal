package com.sujan.usermanagementportal.dto;

import com.sujan.usermanagementportal.model.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String city;
    private String role;
    private String password;
    private int statusCode;
    private String error;
    private String message;
    private Users users;
}
