package com.sookpeech.restapi.domain.users;

import com.sookpeech.restapi.domain.BaseTimeEntity;
import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.friends.Friends;
import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.practices.Practices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "google_id")
    private String googleTokenId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private int point;

    @OneToMany(mappedBy = "users")
    private List<Practices> practices = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Feedbacks> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Friends> friends = new ArrayList<>();

    @Builder
    public Users(String name, String email, String picture, int point, String googleTokenId){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.point = point;
        this.googleTokenId = googleTokenId;
        this.practices = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.friends = new ArrayList<>();
    }
}
