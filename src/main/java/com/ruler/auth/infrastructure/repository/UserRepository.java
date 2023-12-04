package com.ruler.auth.infrastructure.repository;

import com.ruler.auth.domain.model.RegisterBO;
import com.ruler.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
