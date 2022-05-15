package com.sookpeech.restapi.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String idToken;

    public LoginRequestDto(String idToken){
        this.idToken = idToken;
    }
}
