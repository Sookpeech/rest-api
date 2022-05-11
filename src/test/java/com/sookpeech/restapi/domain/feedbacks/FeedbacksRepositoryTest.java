package com.sookpeech.restapi.domain.feedbacks;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class FeedbacksRepositoryTest {
    @Autowired
    FeedbacksRepository feedbacksRepository;

    @Autowired
    PracticesRepository practicesRepository;

    @AfterEach
    public void cleanup(){
        feedbacksRepository.deleteAll();
        practicesRepository.deleteAll();
    }

    @Test
    public void getFeedbacks(){
        //given
        practicesRepository.save(Practices.builder()
                .title("title")
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PUBLIC)
                .sort(Sort.OFFLINE)
                .build());

        int speed_score = 4;
        String speed_comment = "말 빠르기 코멘트입니다.";
        int tone_score = 3;
        String tone_comment = "목소리 변화율 코멘트입니다.";
        int closing_score = 5;
        String closing_comment = "맺음말 평가 코멘트입니다.";
        Practices savedPractices = practicesRepository.findAll().get(0);

        FeedbacksSaveRequestDto requestDto = FeedbacksSaveRequestDto.builder()
                .initiator(Initiator.FRIEND)
                .speed_score(speed_score)
                .speed_comment(speed_comment)
                .tone_score(tone_score)
                .tone_comment(tone_comment)
                .closing_score(closing_score)
                .closing_comment(closing_comment)
                .practice_id(savedPractices.getId())
                .build();

        feedbacksRepository.save(requestDto.toEntity(savedPractices));

        //when
        List<Feedbacks> feedbacksList = feedbacksRepository.findAll();

        //then
        Feedbacks feedbacks = feedbacksList.get(0);
        assertThat(feedbacks.getInitiator()).isEqualTo(Initiator.FRIEND);
        assertThat(feedbacks.getSpeed_score()).isEqualTo(speed_score);
        assertThat(feedbacks.getSpeed_comment()).isEqualTo(speed_comment);
        assertThat(feedbacks.getTone_score()).isEqualTo(tone_score);
        assertThat(feedbacks.getTone_comment()).isEqualTo(tone_comment);
        assertThat(feedbacks.getClosing_score()).isEqualTo(closing_score);
        assertThat(feedbacks.getClosing_comment()).isEqualTo(closing_comment);

    }

    @Test
    public void getFeedbacksByPracticeId(){
        //given
        practicesRepository.save(Practices.builder()
                .title("title1")
                .audioPath("audioPath1")
                .sensitivity(6)
                .scope(Scope.PUBLIC)
                .sort(Sort.OFFLINE)
                .build());

        practicesRepository.save(Practices.builder()
                .title("title2")
                .audioPath("audioPath2")
                .sensitivity(8)
                .scope(Scope.PRIVATE)
                .sort(Sort.OFFLINE)
                .build());

        practicesRepository.save(Practices.builder()
                .title("title3")
                .audioPath("audioPath3")
                .sensitivity(10)
                .scope(Scope.PUBLIC)
                .sort(Sort.ONLINE)
                .build());

        Practices savedPractices1 = practicesRepository.findAll().get(0);
        Practices savedPractices2 = practicesRepository.findAll().get(1);

        FeedbacksSaveRequestDto requestDto1 = FeedbacksSaveRequestDto.builder()
                .initiator(Initiator.FRIEND)
                .speed_score(4)
                .speed_comment("말 빠르기 코멘트입니다1.")
                .tone_score(3)
                .tone_comment("목소리 변화율 코멘트입니다1.")
                .closing_score(5)
                .closing_comment("맺음말 평가 코멘트입니다1.")
                .practice_id(savedPractices1.getId())
                .build();

        FeedbacksSaveRequestDto requestDto2 = FeedbacksSaveRequestDto.builder()
                .initiator(Initiator.USER)
                .speed_score(2)
                .speed_comment("말 빠르기 코멘트입니다2.")
                .tone_score(1)
                .tone_comment("목소리 변화율 코멘트입니다2.")
                .closing_score(4)
                .closing_comment("맺음말 평가 코멘트입니다2.")
                .practice_id(savedPractices2.getId())
                .build();

        FeedbacksSaveRequestDto requestDto3 = FeedbacksSaveRequestDto.builder()
                .initiator(Initiator.FRIEND)
                .speed_score(4)
                .speed_comment("말 빠르기 코멘트입니다3.")
                .tone_score(5)
                .tone_comment("목소리 변화율 코멘트입니다3.")
                .closing_score(3)
                .closing_comment("맺음말 평가 코멘트입니다3.")
                .practice_id(savedPractices1.getId())
                .build();

        feedbacksRepository.save(requestDto1.toEntity(savedPractices1));
        feedbacksRepository.save(requestDto2.toEntity(savedPractices2));
        feedbacksRepository.save(requestDto3.toEntity(savedPractices1));

        //when
        List<Feedbacks> friendsFeedbacksList = feedbacksRepository.findByInitiatorAndPractices(Initiator.FRIEND, savedPractices1);
        List<Feedbacks> usersFeedbacksList = feedbacksRepository.findByInitiatorAndPractices(Initiator.USER, savedPractices2);


        //then: friendsFeedbacksList test
        assertThat(friendsFeedbacksList.size()).isEqualTo(2);
        Feedbacks fd1 = friendsFeedbacksList.get(0);
        Feedbacks fd2 = friendsFeedbacksList.get(1);
        assertThat(fd1.getClosing_comment()).isEqualTo("맺음말 평가 코멘트입니다1.");
        assertThat(fd1.getPractices().getId()).isEqualTo(savedPractices1.getId());
        assertThat(fd2.getTone_score()).isEqualTo(5);
        assertThat(fd2.getPractices().getId()).isEqualTo(savedPractices1.getId());

        //then: usersFeedbacksList
        assertThat(usersFeedbacksList.size()).isEqualTo(1);
        Feedbacks fd3 = usersFeedbacksList.get(0);
        assertThat(fd3.getClosing_comment()).isEqualTo("맺음말 평가 코멘트입니다2.");
        assertThat(fd3.getPractices().getId()).isEqualTo(savedPractices2.getId());
    }
}