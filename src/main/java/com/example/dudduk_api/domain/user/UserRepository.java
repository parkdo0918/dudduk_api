package com.example.dudduk_api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findBySocialIdAndProvider(String socialId, SocialProvider provider);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsBySocialIdAndProvider(String socialId, SocialProvider provider);
}