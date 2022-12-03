package com.mugi.uac.repos;

import com.mugi.uac.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUserId(Long userId);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
