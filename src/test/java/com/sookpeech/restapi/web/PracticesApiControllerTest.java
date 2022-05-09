package com.sookpeech.restapi.web;

import com.sookpeech.restapi.domain.analysis.State;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.web.dto.analysis.AnalysisUpdateRequestDto;
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
        String videoPath = "path_for_video";
        PracticesSaveRequestDto requestDto = PracticesSaveRequestDto.builder()
                .title(title)
                .videoPath(videoPath)
                .scope(Scope.PRIVATE)
                .build();

        String url = "http://localhost:"+port+"/api/practices";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Practices> all = practicesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getVideoPath()).isEqualTo(videoPath);
        assertThat(all.get(0).getScope()).isEqualTo(Scope.PRIVATE);
    }

    @Test
    public void updatePractices() throws Exception{
        //given
        Practices savedPractices = practicesRepository.save(Practices.builder()
                .title("title")
                .videoPath("path_for_video")
                .scope(Scope.PRIVATE)
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
                .videoPath("path_for_video")
                .scope(Scope.PRIVATE)
                .build());

        Long updateId = savedPractices.getId();
        String expectedState = "COMPLETE";

        AnalysisUpdateRequestDto requestDto = AnalysisUpdateRequestDto.builder()
                .state(State.COMPLETE)
                .build();

        String url = "http://localhost:"+port+"/api/practices/analysis_complete/"+updateId;

        HttpEntity<AnalysisUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class );

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Practices> all = practicesRepository.findAll();
        assertThat(all.get(0).getAnalysis().getState().toString()).isEqualTo(expectedState);
    }
}
