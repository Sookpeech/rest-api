package com.sookpeech.restapi.service.users;

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

    public UsersResponseDto findById(Long id){
        Users entity = usersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new UsersResponseDto(entity);
    }

    @Transactional
    // 사용자 이름 검색 기능
    public List<UsersResponseDto> findByNameContaining(UsersFindRequestDto requestDto){
        List<Users> users = usersRepository.findByNameContaining(requestDto.getName());
        List<UsersResponseDto> usersResponseDtos = new ArrayList<>();
        for (Users u: users){
            usersResponseDtos.add(new UsersResponseDto(u));
        }

        return usersResponseDtos;
    }

    // 친구 요청 기능
    // 친구 코드 확인 = 이메일 아이디 부분 + id
}
