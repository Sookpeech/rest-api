package com.sookpeech.restapi.web.dto.analysis;

import com.sookpeech.restapi.domain.analysis.State;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalysisUpdateRequestDto {
    private State state;

    @Builder
    public AnalysisUpdateRequestDto(State state){
        this.state = state;
    }
}
