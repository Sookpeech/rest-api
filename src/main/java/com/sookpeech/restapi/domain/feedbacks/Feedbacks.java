package com.sookpeech.restapi.domain.feedbacks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Feedbacks extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "initiator")
    private Initiator initiator;

    @Column(name = "speed_score")
    private int speed_score;

    @Column(name = "speed_comment")
    private String speed_comment;

    @Column(name = "tone_score")
    private int tone_score;

    @Column(name = "tone_comment")
    private String tone_comment;

    @Column(name = "closing_score")
    private int closing_score;

    @Column(name = "closing_comment")
    private String closing_comment;

    @OneToOne
    @JoinColumn(name = "practice_id", nullable = false)
    @JsonIgnore
    private Practices practices;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users users; //피드백 작성자

    @Builder
    public Feedbacks(Initiator initiator, int speed_score, String speed_comment, int tone_score, String tone_comment, int closing_score, String closing_comment, Practices practices, Users users){
        this.users = users;
        this.initiator = initiator;
        this.speed_score = speed_score;
        this.speed_comment = speed_comment;
        this.tone_score = tone_score;
        this.tone_comment = tone_comment;
        this.closing_score = closing_score;
        this.closing_comment = closing_comment;
        this.practices = practices;
    }
}
