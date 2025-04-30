package com.ex.mini.user.application;

import com.ex.mini.user.domain.entity.Role;
import com.ex.mini.user.domain.entity.User;
import com.ex.mini.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignService {

    private final UserRepository userRepository;

    public String registerUser(String nickname, String role) {
        Role userRole = null;

        if (role.equals("user")) {
            userRole = Role.USER;
        } else if (role.equals("manager")) {
            userRole = Role.MANAGER;
        } else if (role.equals("admin")) {
            userRole = Role.ADMIN;
        }

        User user = User.builder()
                .nickname(nickname).role(userRole)
                .build();
        User savedUser = userRepository.save(user);

        String result = null;

        if (savedUser.getId() != null) {
            result = "success";
        } else {
            result = "fail";
        }

        return result;
    }

    public User authenticateUser(String nickname) {
        User user = userRepository.findByNickname(nickname).orElse(null);

        return user;
    }
}
