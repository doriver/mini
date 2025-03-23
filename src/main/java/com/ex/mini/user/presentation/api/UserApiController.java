package com.ex.mini.user.presentation.api;

import com.ex.mini.user.application.UserSignService;
import com.ex.mini.user.domain.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserSignService userSignService;
    /*
        회원가입
        매개변수 : nickname , role
        리턴 : 성공, 실패
     */
    @PostMapping("/sign-up")
    public String signUp(@RequestParam("nickname") String nickname, @RequestParam("role") String role) {
        return userSignService.registerUser(nickname, role);
    }

    /*
        로그인
        매개변수 : nickname
        리턴 : 성공, 실패
     */
    @PostMapping("/sign-in")
    public String signIn(@RequestParam("nickname") String nickname, HttpSession session) {
        User user = userSignService.authenticateUser(nickname);

        String result = null;
        if (user == null) {
            result = "fail";
        } else {
            result = "success";
            session.setAttribute("userId", user.getId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("role", user.getRole());
        }
        return result;
    }
}
