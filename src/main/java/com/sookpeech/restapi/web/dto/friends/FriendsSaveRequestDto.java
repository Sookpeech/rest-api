package com.sookpeech.restapi.web.dto.friends;

import com.sookpeech.restapi.domain.friends.Friends;
import com.sookpeech.restapi.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendsSaveRequestDto {
    private Long user_id;
    private Long friend_id;
    private String friendCode;

    @Builder
    public FriendsSaveRequestDto(Long user_id, Long friend_id, String friendCode){
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.friendCode = friendCode;
    }

    public Friends toEntity(Users users){
        return Friends.builder()
                .users(users)
                .friend_id(friend_id)
                .build();
    }
}
