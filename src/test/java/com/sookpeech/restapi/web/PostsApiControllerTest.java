//package com.sookpeech.restapi.web;
//
//import com.sookpeech.restapi.domain.posts.Posts;
//import com.sookpeech.restapi.domain.posts.PostsRepository;
//import com.sookpeech.restapi.domain.practices.*;
//import com.sookpeech.restapi.domain.users.Users;
//import com.sookpeech.restapi.domain.users.UsersRepository;
//import com.sookpeech.restapi.web.dto.posts.PostsSaveRequestDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//public class PostsApiControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @Autowired
//    private PracticesRepository practicesRepository;
//
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @AfterEach
//    public void tearDown() throws Exception{
//        postsRepository.deleteAll();
//        practicesRepository.deleteAll();
//        usersRepository.deleteAll();
//    }
//
//    @Test
//    public void setPosts() throws Exception{
//        //given
//        String title = "title";
//        String content = "content";
//
//        usersRepository.save(Users.builder()
//                .googleTokenId("1")
//                .name("testuser")
//                .email("test@gmail.com")
//                .picture("path")
//                .point(0)
//                .build());
//
//        Users savedUser = usersRepository.findAll().get(0);
//
//        practicesRepository.save(Practices.builder()
//                .title("title")
//                .audioPath("audio_path")
//                .sensitivity(2)
//                .scope(Scope.PUBLIC)
//                .sort(Sort.ONLINE)
//                .gender(Gender.WOMEN)
//                .users(savedUser)
//                .build());
//
//        Practices practices = practicesRepository.findAll().get(0);
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .practice_id(practices.getId())
//                .user_id(savedUser.getId())
//                .build();
//
//        String url = "http://localhost:"+port+"/api/posts";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//        assertThat(all.get(0).getPractices().getId()).isEqualTo(practices.getId());
//        assertThat(all.get(0).getUsers().getGoogleTokenId()).isEqualTo("1");
//    }
//}
