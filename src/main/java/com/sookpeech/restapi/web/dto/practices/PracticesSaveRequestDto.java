package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticesSaveRequestDto {
    private String title;
    private String videoPath;
    private Scope scope;
    private Sort sort;

    @Builder
    public PracticesSaveRequestDto(String title, String videoPath, Scope scope, Sort sort){
        this.title = title;
        this.videoPath = videoPath;
        this.scope = scope;
        this.sort = sort;
    }

    public Practices toEntity(){
        return Practices.builder()
                .title(title)
                .videoPath(videoPath)
                .scope(scope)
                .sort(sort)
                .build();
    }
}
