package com.sookpeech.restapi.web.dto.feedbacks;

import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.feedbacks.Initiator;
import com.sookpeech.restapi.domain.practices.Practices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbacksSaveRequestDto {
    private Initiator initiator;
    private int speed_score;
    private String speed_comment;
    private int tone_score;
    private String tone_comment;
    private int closing_score;
    private String closing_comment;
    private Long practice_id;

    @Builder
    public FeedbacksSaveRequestDto(Initiator initiator, int speed_score, String speed_comment, int tone_score, String tone_comment, int closing_score, String closing_comment, Long practice_id){
        this.initiator = initiator;
        this.speed_score = speed_score;
        this.speed_comment = speed_comment;
        this.tone_score = tone_score;
        this.tone_comment = tone_comment;
        this.closing_score = closing_score;
        this.closing_comment = closing_comment;
        this.practice_id = practice_id;
    }

    public Feedbacks toEntity(Practices practices){
        return Feedbacks.builder()
                .initiator(initiator)
                .speed_score(speed_score)
                .speed_comment(speed_comment)
                .tone_score(tone_score)
                .tone_comment(tone_comment)
                .closing_score(closing_score)
                .closing_comment(closing_comment)
                .practices(practices)
                .build();
    }
}
