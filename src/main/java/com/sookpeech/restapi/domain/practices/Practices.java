package com.sookpeech.restapi.domain.practices;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.analysis.State;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Practices extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "video_path", nullable = false)
    private String videoPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
    private Scope scope;

    @Enumerated(EnumType.STRING)
    @Column(name = "sort", nullable = false)
    private Sort sort;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;

    @Builder
    public Practices(String title, String videoPath, Scope scope, Sort sort){
        this.title = title;
        this.videoPath = videoPath;
        this.scope = scope;
        this.sort = sort;
        this.analysis = Analysis.builder()
                .state(State.INCOMPLETE)
                .build();
    }

    public void update(String title, Scope scope){
        this.title = title;
        this.scope = scope;
    }
}
