package com.sookpeech.restapi.web.dto.posts;

import com.sookpeech.restapi.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long practice_id;
//    private Long user_id;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.practice_id = entity.getPractices().getId();
//        this.user_id = entity.getUsers().getId();
    }
}
