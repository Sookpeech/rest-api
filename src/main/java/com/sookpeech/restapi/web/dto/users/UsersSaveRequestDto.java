package com.sookpeech.restapi.web.dto.users;

import com.sookpeech.restapi.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String googleTokenId;
    private String name;
    private String email;
    private String picture;
    private int point;

    @Builder
    public UsersSaveRequestDto(String googleTokenId, String name, String email, String picture, int point){
        this.googleTokenId = googleTokenId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.point = point;
    }

    public Users toEntity(){
        return Users.builder()
                .googleTokenId(googleTokenId)
                .name(name)
                .email(email)
                .picture(picture)
                .point(point)
                .build();
    }
}
