package com.sookpeech.restapi.domain.practices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.analysis.State;
import com.sookpeech.restapi.domain.users.Users;
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
    private String audioPath;

    @Column(name = "sensitivity", nullable = false)
    private int sensitivity;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
    private Scope scope;

    @Enumerated(EnumType.STRING)
    @Column(name = "sort", nullable = false)
    private Sort sort;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users users;

    @Builder
    public Practices(String title, String audioPath, int sensitivity, Scope scope, Sort sort, Users users, Gender gender){
        this.title = title;
        this.audioPath = audioPath;
        this.sensitivity = sensitivity;
        this.scope = scope;
        this.sort = sort;
        this.analysis = Analysis.builder()
                .state(State.INCOMPLETE)
                .build();
        this.users = users;
        this.gender = gender;
    }

    public void update(String title, Scope scope){
        this.title = title;
        this.scope = scope;
    }
}
