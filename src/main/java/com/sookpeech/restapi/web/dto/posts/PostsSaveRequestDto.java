package com.sookpeech.restapi.web.dto.posts;

import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.practices.Practices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private Long practice_id;

    @Builder
    public PostsSaveRequestDto(String title, String content, Long practice_id){
        this.title = title;
        this.content = content;
        this.practice_id = practice_id;
    }

    public Posts toEntity(Practices practices){
        return Posts.builder()
                .title(title)
                .content(content)
                .practices(practices)
                .build();
    }
}
