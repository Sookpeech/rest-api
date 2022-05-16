package com.sookpeech.restapi.web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.sookpeech.restapi.config.auth.LoginService;
import com.sookpeech.restapi.config.auth.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@RestController
public class UsersApiController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public LoginRequestDto Login(@RequestBody LoginRequestDto loginRequestDto) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = loginService.validateIDToken(loginRequestDto);
        if (idToken==null){
            System.out.println("Invalid ID token");
            return LoginRequestDto.builder()
                    .idToken(loginRequestDto.getIdToken())
                    .user_id(-1L)
                    .build();
        }
        else {
            Long user_id = loginService.signInOrLogIn(idToken);
            return LoginRequestDto.builder()
                    .idToken(loginRequestDto.getIdToken())
                    .user_id(user_id)
                    .build();
        }
    }
}
