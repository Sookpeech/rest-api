package com.sookpeech.restapi.domain.practices;

import com.sookpeech.restapi.domain.analysis.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PracticesRepositoryTest {
    @Autowired
    PracticesRepository practicesRepository;

    @AfterEach
    public void cleanup(){
        practicesRepository.deleteAll();
    }

    @Test
    public void getPractices(){
        //given
        String title = "연습 제목";
        String videoPath = "비디오주소";

        practicesRepository.save(Practices.builder()
                .title(title)
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PRIVATE)
                .sort(Sort.OFFLINE)
                .build());

        //when
        List<Practices> practicesList = practicesRepository.findAll();

        //then
        Practices practices = practicesList.get(0);
        assertThat(practices.getTitle()).isEqualTo(title);
        assertThat(practices.getAudioPath()).isEqualTo(videoPath);
        assertThat(practices.getScope()).isEqualTo(Scope.PRIVATE);
        assertThat(practices.getSort()).isEqualTo(Sort.OFFLINE);
        // analysis check
        assertThat(practices.getAnalysis().getState()).isEqualTo(State.INCOMPLETE);
        //analysisContent check
        assertThat(practices.getAnalysis().getAnalysisContents().getIntegration()).isEqualTo(null);
    }

    @Test
    public void BaseTimeEntityEnabling(){
        //given
        LocalDateTime now = LocalDateTime.of(2022, 5, 9, 0, 0, 0);
        practicesRepository.save(Practices.builder()
                .title("title")
                .audioPath("path")
                .sensitivity(6)
                .scope(Scope.PRIVATE)
                .sort(Sort.ONLINE)
                .build());

        //when
        List<Practices> practicesList = practicesRepository.findAll();

        //then
        Practices practices = practicesList.get(0);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> createDate="+practices.getCreatedDate()+",modifiedDate="+practices.getModifiedDate());
        assertThat(practices.getCreatedDate().isAfter(now));
        assertThat(practices.getModifiedDate().isAfter(now));
    }
}
