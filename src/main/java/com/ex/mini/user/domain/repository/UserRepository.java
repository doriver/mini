package com.ex.mini.user.domain.repository;

import com.ex.mini.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
