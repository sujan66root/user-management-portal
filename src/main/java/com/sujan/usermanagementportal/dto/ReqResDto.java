package com.sujan.usermanagementportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqResDto {
    private int statusCode;
    private String error;
    private String message;
}
