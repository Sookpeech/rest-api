package com.sookpeech.restapi.config.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.sookpeech.restapi.config.auth.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class LoginService {

    public void validateIDToken(LoginRequestDto loginRequestDto) throws GeneralSecurityException, IOException {
        System.out.println(">>validIDToken idtoken =" + loginRequestDto.getIdToken());
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("606586417283-4m266cgls2542mopqnoniklt3f71bsqa.apps.googleusercontent.com")) //web client ID
                .build();

        GoogleIdToken idToken = verifier.verify(loginRequestDto.getIdToken());
        if (idToken!=null){
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("USER ID: "+userId);

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            System.out.println("email="+email);
            System.out.println("name="+name);
            System.out.println("prictureUrl="+pictureUrl);
        }
        else{
            System.out.println("Invalid ID token");
        }
    }
}
