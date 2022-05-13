package com.sookpeech.restapi.web.dto.posts;

import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private Long practice_id;
//    private Long user_id;

    @Builder
    public PostsSaveRequestDto(String title, String content, Long practice_id){
        this.title = title;
        this.content = content;
        this.practice_id = practice_id;
//        this.user_id = user_id;
    }

    public Posts toEntity(Practices practices){
        return Posts.builder()
                .title(title)
                .content(content)
                .practices(practices)
//                .users(users)
                .build();
    }
}
