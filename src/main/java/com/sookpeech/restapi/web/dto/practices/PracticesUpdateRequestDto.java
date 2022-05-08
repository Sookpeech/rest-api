package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.practices.Scope;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticesUpdateRequestDto {
    private String title;
    private Scope scope;

    @Builder
    public PracticesUpdateRequestDto(String title, Scope scope){
        this.title = title;
        this.scope = scope;
    }
}
