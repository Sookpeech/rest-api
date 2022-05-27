package com.sookpeech.restapi.web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.sookpeech.restapi.config.auth.LoginService;
import com.sookpeech.restapi.config.auth.dto.LoginRequestDto;
import com.sookpeech.restapi.service.users.UsersService;
import com.sookpeech.restapi.web.dto.users.UsersFindRequestDto;
import com.sookpeech.restapi.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UsersApiController {
    private final LoginService loginService;
    private final UsersService usersService;

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

    @GetMapping("/api/users/{id}")
    public UsersResponseDto findById(@PathVariable Long id){
        return usersService.findById(id);
    }

    @GetMapping("/api/users/search")
    public List<UsersResponseDto> findByNameContaining(@RequestBody UsersFindRequestDto requestDto){
        return usersService.findByNameContaining(requestDto);
    }
}
