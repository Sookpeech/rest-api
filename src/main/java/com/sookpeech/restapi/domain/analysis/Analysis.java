package com.sookpeech.restapi.domain.analysis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state")
    private State state;

    @Builder
    public Analysis(State state){
        this.state = state;
    }

    public void update(State state){
        this.state = state;
    }
}
