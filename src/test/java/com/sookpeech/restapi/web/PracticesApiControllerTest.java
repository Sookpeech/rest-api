package com.sookpeech.restapi.web;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import com.sookpeech.restapi.web.dto.analysisContents.AnalysisContentsUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PracticesApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PracticesRepository practicesRepository;

    @AfterEach
    public void tearDown() throws Exception{
        practicesRepository.deleteAll();
    }

    @Test
    public void setPractices() throws Exception{
        //given
        String title = "title";
        String audioPath = "path_for_audio";
        PracticesSaveRequestDto requestDto = PracticesSaveRequestDto.builder()
                .title(title)
                .audioPath(audioPath)
                .sensitivity(10)
                .scope(Scope.PRIVATE)
                .sort(Sort.OFFLINE)
                .build();

        String url = "http://localhost:"+port+"/api/practices";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Practices> all = practicesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getAudioPath()).isEqualTo(audioPath);
        assertThat(all.get(0).getSensitivity()).isEqualTo(10);
        assertThat(all.get(0).getScope()).isEqualTo(Scope.PRIVATE);
        assertThat(all.get(0).getSort().toString()).isEqualTo("OFFLINE");
    }

    @Test
    public void updatePractices() throws Exception{
        //given
        Practices savedPractices = practicesRepository.save(Practices.builder()
                .title("title")
                .audioPath("path_for_audio")
                .sensitivity(8)
                .scope(Scope.PRIVATE)
                .sort(Sort.ONLINE)
                .build());

        Long updateId = savedPractices.getId();
        String expectedTitle = "title2";
        Scope expectedScope = Scope.PUBLIC;

        PracticesUpdateRequestDto requestDto = PracticesUpdateRequestDto.builder()
                .title(expectedTitle)
                .scope(expectedScope)
                .build();

        String url = "http://localhost:"+port+"/api/practices/"+updateId;

        HttpEntity<PracticesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Practices> all = practicesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getScope()).isEqualTo(expectedScope);
    }

    @Test
    public void changeState() throws Exception{
        //given
        Practices savedPractices = practicesRepository.save(Practices.builder()
                .title("title")
                .audioPath("path_for_audio")
                .sensitivity(7)
                .scope(Scope.PRIVATE)
                .sort(Sort.ONLINE)
                .build());

        Long updateId = savedPractices.getId();
        String expectedState = "COMPLETE";
        String expectedIntegration = "종합평가입니다.";
        String expectedMovement = "움직임과 제스처입니다.";
        String expectedPosture = "전반적인 자세입니다.";
        String expectedSpeed = "말하기 속도입니다.";
        String expectedVolume = "목소리 크기 변화율입니다.";
        String expectedTone = "목소리 높낮이 변화율입니다.";
        String expectedClosing = "맺음말 분석 결과입니다.";

        AnalysisContentsUpdateRequestDto requestDto = AnalysisContentsUpdateRequestDto.builder()
                .integration(expectedIntegration)
                .movement(expectedMovement)
                .posture(expectedPosture)
                .speed(expectedSpeed)
                .volume(expectedVolume)
                .tone(expectedTone)
                .closing(expectedClosing)
                .build();


        String url = "http://localhost:"+port+"/api/practices/analysis_complete/"+updateId;

        HttpEntity<AnalysisContentsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class );

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Practices> all = practicesRepository.findAll();
        // analysisContent check
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getIntegration()).isEqualTo(expectedIntegration);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getMovement()).isEqualTo(expectedMovement);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getPosture()).isEqualTo(expectedPosture);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getSpeed()).isEqualTo(expectedSpeed);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getVolume()).isEqualTo(expectedVolume);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getTone()).isEqualTo(expectedTone);
        assertThat(all.get(0).getAnalysis().getAnalysisContents().getClosing()).isEqualTo(expectedClosing);
        //analysis state check
        assertThat(all.get(0).getAnalysis().getState().toString()).isEqualTo(expectedState);
    }
}
