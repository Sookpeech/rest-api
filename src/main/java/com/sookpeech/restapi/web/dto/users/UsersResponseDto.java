package com.sookpeech.restapi.web.dto.users;

import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.friends.Friends;
import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.users.Users;
import lombok.Getter;

import java.util.List;

@Getter
public class UsersResponseDto {
    private String name;
    private String email;
    private String picture;
    private int point;
    private List<Practices> practices;
    private List<Posts> posts;
    private List<Feedbacks> feedbacks;
    private Friends friends;

    public UsersResponseDto(Users entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.point = entity.getPoint();
        this.practices = entity.getPractices();
        this.posts = entity.getPosts();
        this.feedbacks = entity.getFeedbacks();
        this.friends = entity.getFriends();
    }
}
