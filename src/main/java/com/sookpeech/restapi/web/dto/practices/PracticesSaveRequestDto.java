package com.sookpeech.restapi.web.dto.practices;

import com.sookpeech.restapi.domain.practices.Gender;
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
    private int move_sensitivity;
    private int eyes_sensitivity;
    private Scope scope;
    private Sort sort;
    private Long user_id;
    private Gender gender;

    @Builder
    public PracticesSaveRequestDto(String title, int move_sensitivity, int eyes_sensitivity, Scope scope, Sort sort, Gender gender, Long user_id){
        this.title = title;
        this.move_sensitivity = move_sensitivity;
        this.eyes_sensitivity = eyes_sensitivity;
        this.scope = scope;
        this.sort = sort;
        this.user_id = user_id;
        this.gender = gender;
    }

    public Practices toEntity(Users users){
        return Practices.builder()
                .title(title)
                .move_sensitivity(move_sensitivity)
                .eyes_sensitivity(eyes_sensitivity)
                .scope(scope)
                .sort(sort)
                .users(users)
                .gender(gender)
                .build();
    }
}
