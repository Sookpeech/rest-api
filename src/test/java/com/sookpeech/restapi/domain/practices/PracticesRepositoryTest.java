package com.sookpeech.restapi.domain.practices;

import com.sookpeech.restapi.domain.analysis.State;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.service.practices.PracticesService;
import com.sookpeech.restapi.web.dto.practices.PracticesFindRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PracticesRepositoryTest {
    @Autowired
    PracticesRepository practicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PracticesService practicesService;

    @AfterEach
    public void cleanup(){
        practicesRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test
    public void getPractices(){
        //given
        String title = "연습 제목";
        String audioPath = "audioPath";

        usersRepository.save(Users.builder()
                .googleTokenId("1")
                .name("testuser")
                .email("test@gmail.com")
                .picture("path")
                .point(0)
                .build());

        Users savedUser = usersRepository.findAll().get(0);

        practicesRepository.save(Practices.builder()
                .title(title)
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PRIVATE)
                .sort(Sort.OFFLINE)
                .gender(Gender.WOMEN)
                .users(savedUser)
                .build());

        //when
        List<Practices> practicesList = practicesRepository.findAll();

        //then
        Practices practices = practicesList.get(0);
        assertThat(practices.getTitle()).isEqualTo(title);
        assertThat(practices.getAudioPath()).isEqualTo(audioPath);
        assertThat(practices.getScope()).isEqualTo(Scope.PRIVATE);
        assertThat(practices.getSort()).isEqualTo(Sort.OFFLINE);
        // analysis check
        assertThat(practices.getAnalysis().getState()).isEqualTo(State.INCOMPLETE);
        //analysisContent check
        assertThat(practices.getAnalysis().getAnalysisContents().getIntegration()).isEqualTo(null);
        assertThat(practices.getUsers().getEmail()).isEqualTo("test@gmail.com");
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

    @Test
    @Transactional
    public void findByTitleContainingTest(){
        // given
        usersRepository.save(Users.builder()
                .googleTokenId("1")
                .name("testuser")
                .email("test@gmail.com")
                .picture("path")
                .point(0)
                .build());

        Users savedUser = usersRepository.findAll().get(0);

        practicesRepository.save(Practices.builder()
                .title("title1")
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PUBLIC)
                .sort(Sort.ONLINE)
                .gender(Gender.WOMEN)
                .users(savedUser)
                .build());

        practicesRepository.save(Practices.builder()
                .title("title2")
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PRIVATE)
                .sort(Sort.OFFLINE)
                .gender(Gender.WOMEN)
                .users(savedUser)
                .build());

        practicesRepository.save(Practices.builder()
                .title("test1")
                .audioPath("audioPath")
                .sensitivity(6)
                .scope(Scope.PRIVATE)
                .sort(Sort.ONLINE)
                .gender(Gender.MEN)
                .users(savedUser)
                .build());

        PracticesFindRequestDto requestDto = PracticesFindRequestDto.builder()
                .title("title")
                .build();

        // when
        List<PracticesResponseDto> responseDtos = practicesService.findByTitleContaining(1L, requestDto);

        // then
        assertThat(responseDtos.size()).isEqualTo(2);
    }
}
