package com.sookpeech.restapi.domain.analysis;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.analysisContents.AnalysisContents;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Analysis extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id", nullable = false)
    private AnalysisContents analysisContents;

    @Builder
    public Analysis(State state){
        this.state = state;
        this.analysisContents = AnalysisContents.builder().build();
    }

    public void update(State state){
        this.state = state;
    }
}
