package com.sookpeech.restapi.service.users;

import com.sookpeech.restapi.domain.friends.FriendsRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.users.UsersFindRequestDto;
import com.sookpeech.restapi.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final FriendsRepository friendsRepository;

    public UsersResponseDto findById(Long id){
        Users entity = usersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new UsersResponseDto(entity);
    }

    // 사용자 이름 검색 기능
    @Transactional
    public List<UsersResponseDto> findByNameContaining(String name){
        List<Users> users = usersRepository.findByNameContaining(name);
        List<UsersResponseDto> usersResponseDtos = new ArrayList<>();
        for (Users u: users){
            usersResponseDtos.add(new UsersResponseDto(u));
        }

        return usersResponseDtos;
    }
}
