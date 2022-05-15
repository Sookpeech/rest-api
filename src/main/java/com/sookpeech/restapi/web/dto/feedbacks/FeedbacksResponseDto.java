package com.sookpeech.restapi.web.dto.feedbacks;

import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.feedbacks.Initiator;
import lombok.Getter;

@Getter
public class FeedbacksResponseDto {
    private Long id;
    private Initiator initiator;
    private int speed_score;
    private String speed_comment;
    private int tone_score;
    private String tone_comment;
    private int closing_score;
    private String closing_comment;
    private Long practice_id;
//    private Long user_id;

    public FeedbacksResponseDto(Feedbacks entity){
        this.id = entity.getId();
        this.initiator = entity.getInitiator();
        this.speed_score = entity.getSpeed_score();
        this.speed_comment = entity.getSpeed_comment();
        this.tone_score = entity.getTone_score();
        this.tone_comment = entity.getTone_comment();
        this.closing_score = entity.getClosing_score();
        this.closing_comment = entity.getClosing_comment();
        this.practice_id = entity.getPractices().getId();
//        this.user_id = entity.getUsers().getId();
    }
}
