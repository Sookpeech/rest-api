//package com.sookpeech.restapi.domain.users;
//
//import com.sookpeech.restapi.service.users.UsersService;
//import com.sookpeech.restapi.web.dto.users.UsersFindRequestDto;
//import com.sookpeech.restapi.web.dto.users.UsersResponseDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//public class UsersRepositoryTest {
//    @Autowired
//    UsersRepository usersRepository;
//
//    @Autowired
//    UsersService usersService;
//
//    @AfterEach
//    public void cleanup(){
//        usersRepository.deleteAll();
//    }
//
//    @Test
//    @Transactional
//    public void findUserByNameContaining(){
//        //given
//        usersRepository.save(Users.builder()
//                .googleTokenId("1")
//                .name("testuser1")
//                .email("test1@gmail.com")
//                .picture("path1")
//                .point(0)
//                .build());
//
//        usersRepository.save(Users.builder()
//                .googleTokenId("2")
//                .name("testuser2")
//                .email("test2@gmail.com")
//                .picture("path2")
//                .point(0)
//                .build());
//
//        usersRepository.save(Users.builder()
//                .googleTokenId("3")
//                .name("user3")
//                .email("test3@gmail.com")
//                .picture("path3")
//                .point(0)
//                .build());
//
//        UsersFindRequestDto usersFindRequestDto = UsersFindRequestDto.builder()
//                .name("test")
//                .build();
//
//        // when
//        List<UsersResponseDto> usersList = usersService.findByNameContaining(usersFindRequestDto);
//
//        //then
//        assertThat(usersList.size()).isEqualTo(2);
//        assertThat(usersList.get(0).getName()).isEqualTo("testuser1");
//        assertThat(usersList.get(1).getEmail()).isEqualTo("test2@gmail.com");
//    }
//}
