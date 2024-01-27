package com.PiperChat.User.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDto {
    @JsonProperty("token")
    private String accessToken;

    public AuthResponseDto(String accessToken){
        this.accessToken = accessToken;
    }
}
