package com.ex.mini.common.argumentResolver;

import com.ex.mini.user.domain.entity.Role;
import lombok.Getter;

@Getter
public class UserInfo {
    private Long userId;
    private Role role;

    protected UserInfo(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
