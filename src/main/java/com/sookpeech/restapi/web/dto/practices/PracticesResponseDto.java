package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import lombok.Getter;

@Getter
public class PracticesResponseDto {
    private Long id;
    private String title;
    private String videoPath;
    private Scope scope;

    public PracticesResponseDto(Practices entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.videoPath = entity.getVideoPath();
        this.scope = entity.getScope();
    }
}
