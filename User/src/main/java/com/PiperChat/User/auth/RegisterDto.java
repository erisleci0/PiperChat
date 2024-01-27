package com.PiperChat.User.auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
