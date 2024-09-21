package com.sujan.usermanagementportal.dto;

import com.sujan.usermanagementportal.model.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersListReqResDto {
    private List<Users> userslist;
    private int statusCode;
    private String message;
    private String error;
}
