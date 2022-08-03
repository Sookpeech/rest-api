package com.sookpeech.restapi.domain.analysisContents;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class AnalysisContents extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_duration")
    private float total_duration;

    @Column(name = "inclined_duration")
    private float inclined_duration;

    @Column(name = "first_duration")
    private float first_duration;

    @Column(name = "second_duration")
    private float second_duration;

    @Column(name = "third_duration")
    private float third_duration;

    @Column(name = "script_duration")
    private float script_duration;

    @Column(name = "around_duration")
    private float around_duration;

    @Column(name = "face_move_duration")
    private float face_move_duration;

    @Column(name = "speed")
    private float speed;

    @Column(name = "closing_remarks")
    private float closing_remarks;

    @Column(name = "shimmer")
    private float shimmer;

    @Column(name = "jitter")
    private float jitter;

    @Builder
    public AnalysisContents(
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

    public void update(
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
