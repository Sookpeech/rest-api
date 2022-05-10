package com.sookpeech.restapi.domain.posts;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.practices.Practices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "practice_id", nullable = false)
    private Practices practices;

    @Builder
    public Posts(String title, String content, Practices practices){
        this.title = title;
        this.content = content;
        this.practices = practices;
    }
}
