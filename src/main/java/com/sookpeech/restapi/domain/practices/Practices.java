package com.sookpeech.restapi.domain.practices;

import com.sookpeech.restapi.domain.BaseTimeEntity;
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

    @Column(name = "title", length = 15, nullable = false)
    private String title;

    @Column(name = "video_path", nullable = false)
    private String videoPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope")
    private Scope scope;

    @Builder
    public Practices(String title, String videoPath, Scope scope){
        this.title = title;
        this.videoPath = videoPath;
        this.scope = scope;
    }

    public void update(String title, Scope scope){
        this.title = title;
        this.scope = scope;
    }
}
