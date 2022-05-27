package com.sookpeech.restapi.domain.friends;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Friends extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "friend_id")
    private Long friend_id;

    @Builder
    public Friends(Long friend_id){
        this.friend_id = friend_id;
    }
}
