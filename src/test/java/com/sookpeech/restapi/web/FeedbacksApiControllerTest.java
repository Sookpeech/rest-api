package com.sookpeech.restapi.web;

import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.feedbacks.FeedbacksRepository;
import com.sookpeech.restapi.domain.feedbacks.Initiator;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class FeedbacksApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FeedbacksRepository feedbacksRepository;

    @Autowired
    private PracticesRepository practicesRepository;

    @AfterEach
    public void cleanup() throws Exception{
        feedbacksRepository.deleteAll();
        practicesRepository.deleteAll();
    }

    @Test
    public void setFeedbacks() throws Exception{
        //given
        practicesRepository.save(Practices.builder()
                .title("title")
                .audioPath("audio_path")
                .sensitivity(9)
                .scope(Scope.PUBLIC)
                .sort(Sort.ONLINE)
                .build());

        Practices practices = practicesRepository.findAll().get(0);
        FeedbacksSaveRequestDto requestDto = FeedbacksSaveRequestDto.builder()
                .initiator(Initiator.FRIEND)
                .speed_score(4)
                .speed_comment("말 빠르기 코멘트입니다.")
                .tone_score(3)
                .tone_comment("목소리 변화율 코멘트입니다.")
                .closing_score(5)
                .closing_comment("맺음말 평가 코멘트입니다.")
                .practice_id(practices.getId())
                .build();

        String url = "http://localhost:"+port+"/api/feedbacks";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Feedbacks> all = feedbacksRepository.findAll();
        assertThat(all.get(0).getInitiator()).isEqualTo(Initiator.FRIEND);
        assertThat(all.get(0).getTone_score()).isEqualTo(3);
        assertThat(all.get(0).getClosing_comment()).isEqualTo("맺음말 평가 코멘트입니다.");
        assertThat(all.get(0).getPractices().getId()).isEqualTo(practices.getId());
    }
}
