package com.sookpeech.restapi.service.friends;

import com.sookpeech.restapi.domain.friends.Friends;
import com.sookpeech.restapi.domain.friends.FriendsRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.friends.FriendsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendsService {
    public final FriendsRepository friendsRepository;
    public final UsersRepository usersRepository;

    // 친구 요청 기능
    // 성공 시 요청한 친구의 id 반환, 친구 코드가 일치하지 않을 경우 -1 반환, 이미 친구 추가된 사용자인 경우 -2 반환
    @Transactional
    public Long sendFriendRequest(Long id, FriendsSaveRequestDto requestDto){
        Users user = usersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        Users friend = usersRepository.findById(requestDto.getFriend_id())
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+requestDto.getFriend_id()));

        // 친구 코드 확인 = 이메일 아이디 부분 + id
        String code = friend.getEmail().split("@")[0]+friend.getId().toString();
        if (!code.equals(requestDto.getFriendCode())){
            return -1L;
        }

        // 이미 친구 추가되어 있는 경우
        for (Friends f: user.getFriends()){
            if (f.getFriend_id().equals(friend.getId())){
                return -2L;
            }
        }

        // 1) 현재 사용자의 friend list에 friend_id 추가
        friendsRepository.save(requestDto.toEntity(user, friend.getName()));

        // 2) 친구의 friend list에 현재 사용자의 id 추가
        Friends newone = Friends.builder()
                .friend_id(id)
                .users(friend)
                .friend_name(user.getName())
                .build();
        friendsRepository.save(newone);

        return friend.getId();
    }

}
