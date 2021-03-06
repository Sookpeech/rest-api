package com.sookpeech.restapi.service.posts;

import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.posts.PostsRepository;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.posts.PostsResponseDto;
import com.sookpeech.restapi.web.dto.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Autowired
    PracticesRepository practicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Practices practices = practicesRepository.findById(requestDto.getPractice_id())
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+requestDto.getPractice_id()));
        Users users = usersRepository.findById(requestDto.getUser_id())
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+requestDto.getUser_id()));
        return postsRepository.save(requestDto.toEntity(practices, users)).getId();
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto> findAll(){
        List<Posts> postsList = postsRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostsResponseDto> responseDtoList = new ArrayList<>();
        for (Posts posts : postsList) {
            responseDtoList.add(new PostsResponseDto(posts));
        }
        return responseDtoList;
    }

    @Transactional
    public Long deleteById(Long id){
        postsRepository.deleteById(id);

        return id;
    }
}
