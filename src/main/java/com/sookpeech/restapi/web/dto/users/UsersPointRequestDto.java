package com.sookpeech.restapi.web.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersPointRequestDto {
    private Long id;
    private int point;
    private String instruction;

    @Builder
    public UsersPointRequestDto(Long id, int point, String instruction){
        this.id = id;
        this.point = point;
        this.instruction = instruction;
    }
}
