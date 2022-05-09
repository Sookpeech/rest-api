package com.sookpeech.restapi.web.dto.analysisContents;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalysisContentsUpdateRequestDto {
    private String integration;
    private String movement;
    private String posture;
    private String speed;
    private String volume;
    private String tone;
    private String closing;

    @Builder
    public AnalysisContentsUpdateRequestDto(String integration, String movement, String posture, String speed, String volume, String tone, String closing){
        this.integration = integration;
        this.movement = movement;
        this.posture = posture;
        this.speed = speed;
        this.volume = volume;
        this.tone = tone;
        this.closing = closing;
    }
}
