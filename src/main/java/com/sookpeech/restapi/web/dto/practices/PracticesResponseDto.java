package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import lombok.Getter;

@Getter
public class PracticesResponseDto {
    private Long id;
    private String title;
    private String audioPath;
    private int sensitivity;
    private Scope scope;
    private Sort sort;
    private Analysis analysis;

    public PracticesResponseDto(Practices entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.sensitivity = entity.getSensitivity();
        this.audioPath = entity.getAudioPath();
        this.scope = entity.getScope();
        this.sort = entity.getSort();
        this.analysis = entity.getAnalysis();
    }
}
