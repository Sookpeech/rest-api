package com.sookpeech.restapi.domain.posts;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import com.sookpeech.restapi.web.dto.posts.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PostsRepositoryTest {
//    @Autowired
//    PostsRepository postsRepository;
//
//    @Autowired
//    PracticesRepository practicesRepository;
//
//    @AfterEach
//    public void cleanup(){
//        postsRepository.deleteAll();
//        practicesRepository.deleteAll();
//    }
//
//    @Test
//    public void getPosts(){
//        //given
//        practicesRepository.save(Practices.builder()
//                .title("title")
//                .audioPath("audioPath")
//                .sensitivity(4)
//                .scope(Scope.PUBLIC)
//                .sort(Sort.OFFLINE)
//                .build());
//
//        String title = "게시글 제목";
//        String content = "게시글 본문";
//        Practices savedPractice = practicesRepository.findAll().get(0);
//
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .practice_id(savedPractice.getId())
//                .build();
//
//        postsRepository.save(requestDto.toEntity(savedPractice));
//
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
//        assertThat(posts.getPractices().getId()).isEqualTo(savedPractice.getId());
//    }
}
