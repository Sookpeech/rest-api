package com.sookpeech.restapi.web;

import com.sookpeech.restapi.service.friends.FriendsService;
import com.sookpeech.restapi.web.dto.friends.FriendsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FriendsApiController {
    private final FriendsService friendsService;

    // 친구 요청하기
    @PostMapping("/api/friends")
    public Long sendFriendRequest(@RequestBody FriendsSaveRequestDto requestDto){
        return friendsService.sendFriendRequest(requestDto);
    }
}
