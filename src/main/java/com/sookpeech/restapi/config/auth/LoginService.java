package com.sookpeech.restapi.config.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.sookpeech.restapi.config.auth.dto.LoginRequestDto;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.users.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@PropertySource("classpath:config.properties")
public class LoginService {

    @Value("${Web-Client-Id}")
    private String webClientId;

    private final UsersRepository usersRepository;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto){
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    public GoogleIdToken validateIDToken(LoginRequestDto loginRequestDto) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(webClientId)) //web client ID
                .build();

        return verifier.verify(loginRequestDto.getIdToken());
    }


    public Long signInOrLogIn(GoogleIdToken idToken){
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();
        Optional<Users> users = usersRepository.findByGoogleTokenId(userId);
        if (users.isEmpty()){
            //SignIn
            UsersSaveRequestDto requestDto = UsersSaveRequestDto.builder()
                    .googleTokenId(userId)
                    .name((String)payload.get("name"))
                    .email(payload.getEmail())
                    .picture((String) payload.get("picture"))
                    .point(0)
                    .build();

            System.out.println(">>>>>>>SignIn!");
            return this.save(requestDto);
        }
        else {
            //LogIn
            System.out.println(">>>>>>>LogIn!");
            return users.get().getId();
        }
    }
}
