package com.ex.mini.common.utils;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.Expected4xxException;
import com.ex.mini.user.domain.entity.Role;

public class UserUtils {

    public static void checkLogin(Long userId) {
        if (userId == null) { // 이렇게 아니면 어떻게 처리하는지 알아볼만 할듯
            throw new Expected4xxException(ErrorCode.NEED_TO_LOGIN);
        }
    }
    public static void checkManagerAdmin(Role role) {
        if (! (role == Role.MANAGER || role == Role.ADMIN)) { // 이렇게 아니면 어떻게 처리하는지 알아볼만 할듯
            throw new Expected4xxException(ErrorCode.DONT_HAVE_AUTHORITY);
        }
    }
}
