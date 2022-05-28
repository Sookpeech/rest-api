package com.sookpeech.restapi.domain.posts;

import com.sookpeech.restapi.domain.practices.Practices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByPractices(Practices practices);
}
