package com.sookpeech.restapi.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String idToken;
    private Long user_id;

    @Builder
    public LoginRequestDto(String idToken, Long user_id){
        this.idToken = idToken;
        this.user_id = user_id;
    }
}
