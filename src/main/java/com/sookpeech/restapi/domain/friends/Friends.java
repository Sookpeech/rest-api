package com.sookpeech.restapi.domain.friends;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.users.Users;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users users;

    @Column(name = "friend_id")
    private Long friend_id;

    @Column(name = "friend_name")
    private String friend_name;

    @Builder
    public Friends(Long friend_id, String friend_name, Users users){
        this.friend_id = friend_id;
        this.friend_name = friend_name;
        this.users = users;
    }
}
