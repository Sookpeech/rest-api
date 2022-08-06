//package com.sookpeech.restapi.domain.friends;
//
//import com.sookpeech.restapi.domain.users.Users;
//import com.sookpeech.restapi.domain.users.UsersRepository;
//import com.sookpeech.restapi.service.friends.FriendsService;
//import com.sookpeech.restapi.web.dto.friends.FriendsSaveRequestDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//public class FriendsRepositoryTest {
//    @Autowired
//    FriendsRepository friendsRepository;
//
//    @Autowired
//    UsersRepository usersRepository;
//
//    @Autowired
//    FriendsService friendsService;
//
//    @AfterEach
//    public void cleanup(){
//        friendsRepository.deleteAll();
//        usersRepository.deleteAll();
//    }
//
//    @Test
//    @Transactional
//    public void sendFriendRequestTest(){
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
//        Users user1 = usersRepository.findAll().get(0);
//        Users user2 = usersRepository.findAll().get(1);
//
//        FriendsSaveRequestDto requestDto = FriendsSaveRequestDto.builder()
//                .friend_id(2L)
//                .friendCode("test22")
//                .build();
//
//        //when
//        Long result = friendsService.sendFriendRequest(1L, requestDto);
//
//        //then
//        assertThat(result).isGreaterThan(0L);
//        assertThat(user1.getFriends().size()).isGreaterThan(0);
////        assertThat(user1.getFriends().get(0).getFriend_id()).isEqualTo(2L);
////        assertThat(user2.getFriends().get(0).getFriend_id()).isEqualTo(1L);
//    }
//}
