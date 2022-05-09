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

    @Column(name = "integration") // 종합평가
    private String integration;

    @Column(name = "movement") // 움직임과 제스처(오프라인=제스처, 온라인=얼굴 움직임)
    private String movement;

    @Column(name = "posture") // 전반적인 자세
    private String posture;

    @Column(name = "speed") // 말하기 속도
    private String speed;

    @Column(name = "volume") // 목소리 크기 변화율
    private String volume;

    @Column(name = "tone") // 목소리 높낮이 변화율
    private String tone;

    @Column(name = "closing") //맺음말
    private String closing;

    @Builder
    public AnalysisContents(String integration, String movement, String posture, String speed, String volume, String tone, String closing){
        this.integration = integration;
        this.movement = movement;
        this.posture = posture;
        this.speed = speed;
        this.volume = volume;
        this.tone = tone;
        this.closing = closing;
    }

    public void update(String integration, String movement, String posture, String speed, String volume, String tone, String closing){
        this.integration = integration;
        this.movement = movement;
        this.posture = posture;
        this.speed = speed;
        this.volume = volume;
        this.tone = tone;
        this.closing = closing;
    }

}
