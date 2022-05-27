package com.sookpeech.restapi.web.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersFindRequestDto {
    private String name;

    @Builder
    public UsersFindRequestDto(String name){
        this.name = name;
    }
}
