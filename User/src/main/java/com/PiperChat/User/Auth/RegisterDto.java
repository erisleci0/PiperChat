package com.PiperChat.User.Auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
