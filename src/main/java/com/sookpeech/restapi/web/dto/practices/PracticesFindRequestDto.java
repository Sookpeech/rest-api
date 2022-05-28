package com.sookpeech.restapi.web.dto.practices;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticesFindRequestDto {
    private String title;

    @Builder
    public PracticesFindRequestDto(String title){
        this.title = title;
    }
}
