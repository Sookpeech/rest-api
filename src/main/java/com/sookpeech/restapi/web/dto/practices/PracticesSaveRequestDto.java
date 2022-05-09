package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticesSaveRequestDto {
    private String title;
    private String videoPath;
    private Scope scope;

    @Builder
    public PracticesSaveRequestDto(String title, String videoPath, Scope scope, Analysis analysis){
        this.title = title;
        this.videoPath = videoPath;
        this.scope = scope;
    }

    public Practices toEntity(){
        return Practices.builder()
                .title(title)
                .videoPath(videoPath)
                .scope(scope)
                .build();
    }
}
