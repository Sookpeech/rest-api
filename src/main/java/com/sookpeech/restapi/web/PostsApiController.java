package com.sookpeech.restapi.web;

import com.sookpeech.restapi.config.auth.LoginService;
import com.sookpeech.restapi.config.auth.dto.LoginRequestDto;
import com.sookpeech.restapi.service.posts.PostsService;
import com.sookpeech.restapi.web.dto.posts.PostsResponseDto;
import com.sookpeech.restapi.web.dto.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    private final LoginService loginService;

    // Create
    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    // Read
    @GetMapping("/api/posts")
    public List<PostsResponseDto> findAll(){
        return postsService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    //temporary for user login POST
    @PostMapping("/api/login")
    public LoginRequestDto Login(@RequestBody LoginRequestDto loginRequestDto) throws GeneralSecurityException, IOException {
        System.out.println("idToken = "+loginRequestDto.getIdToken());
        loginService.validateIDToken(loginRequestDto);
        return loginRequestDto;
    }
}
