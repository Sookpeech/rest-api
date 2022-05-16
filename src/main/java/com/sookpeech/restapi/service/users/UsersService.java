package com.sookpeech.restapi.service.users;

import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersResponseDto findById(Long id){
        Users entity = usersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new UsersResponseDto(entity);
    }
}
