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

    @Column(name = "move_sensitivity", nullable = false)
    private int move_sensitivity;

    @Column(name = "eyes_sensitivity", nullable = false)
    private int eyes_sensitivity;

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
    public Practices(String title, int move_sensitivity, int eyes_sensitivity, Scope scope, Sort sort, Users users, Gender gender){
        this.title = title;
        this.move_sensitivity = move_sensitivity;
        this.eyes_sensitivity = eyes_sensitivity;
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
