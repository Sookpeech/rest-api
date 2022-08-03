package com.sookpeech.restapi.web.dto.analysisContents;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalysisContentsUpdateRequestDto {
    private float total_duration;
    private float inclined_duration;
    private float first_duration;
    private float second_duration;
    private float third_duration;
    private float script_duration;
    private float around_duration;
    private float face_move_duration;
    private float speed;
    private float closing_remarks;
    private float shimmer;
    private float jitter;

    @Builder
    public AnalysisContentsUpdateRequestDto(
            float total_duration,
            float inclined_duration,
            float first_duration,
            float second_duration,
            float third_duration,
            float script_duration,
            float around_duration,
            float face_move_duration,
            float speed,
            float closing_remarks,
            float shimmer,
            float jitter
    ){
        this.total_duration = total_duration;
        this.inclined_duration = inclined_duration;
        this.first_duration = first_duration;
        this.second_duration = second_duration;
        this.third_duration = third_duration;
        this.script_duration = script_duration;
        this.around_duration = around_duration;
        this.face_move_duration = face_move_duration;
        this.speed = speed;
        this.closing_remarks = closing_remarks;
        this.shimmer = shimmer;
        this.jitter = jitter;
    }
}
