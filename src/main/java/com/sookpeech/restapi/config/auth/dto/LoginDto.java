package com.sookpeech.restapi.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String idToken;

    public LoginDto(String idToken){
        this.idToken = idToken;
    }
}
