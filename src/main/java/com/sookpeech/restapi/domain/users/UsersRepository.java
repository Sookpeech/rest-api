package com.sookpeech.restapi.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByGoogleTokenId(String googleTokenId);
    List<Users> findByNameContaining(String name);
}
