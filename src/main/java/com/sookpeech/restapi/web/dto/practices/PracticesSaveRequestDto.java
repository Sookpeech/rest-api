package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.domain.practices.Sort;
import com.sookpeech.restapi.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticesSaveRequestDto {
    private String title;
    private String audioPath;
    private int sensitivity;
    private Scope scope;
    private Sort sort;
    private Long user_id;

    @Builder
    public PracticesSaveRequestDto(String title, String audioPath, int sensitivity, Scope scope, Sort sort, Long user_id){
        this.title = title;
        this.audioPath = audioPath;
        this.sensitivity = sensitivity;
        this.scope = scope;
        this.sort = sort;
        this.user_id = user_id;
    }

    public Practices toEntity(Users users){
        return Practices.builder()
                .title(title)
                .audioPath(audioPath)
                .sensitivity(sensitivity)
                .scope(scope)
                .sort(sort)
                .users(users)
                .build();
    }
}
