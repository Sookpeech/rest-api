package com.sookpeech.restapi.service.users;

import com.sookpeech.restapi.domain.friends.FriendsRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.users.UsersFindRequestDto;
import com.sookpeech.restapi.web.dto.users.UsersPointRequestDto;
import com.sookpeech.restapi.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // 포인트 수정
    @Transactional
    public Long changePoint(UsersPointRequestDto requestDto){
        // requestDto.id로 사용자 찾아오기
        Users user = usersRepository.findById(requestDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+requestDto.getId()));

        // reqeustDto.instruction에 따라 포인트 증가-차감 처리
        int newPoint = Objects.equals(requestDto.getInstruction(), "plus") ? user.getPoint() + requestDto.getPoint() : user.getPoint() - requestDto.getPoint();
        newPoint = Math.max(newPoint, 0);

        // 변경 정보 재저장
        user.update(newPoint);

        // id 반환
        return user.getId();
    }
}
