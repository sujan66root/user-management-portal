package com.sujan.usermanagementportal.dto;
import com.sujan.usermanagementportal.model.Users;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReqResDto {
    private Users users;
    private int statusCode;
    private String error;
    private String message;
}
