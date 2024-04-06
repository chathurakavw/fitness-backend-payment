package com.fitness.payment.dto;


import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String fullName;
    private Integer roleId;
}
