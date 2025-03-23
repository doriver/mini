package com.ex.mini.user.presentation.api;

import com.ex.mini.user.application.UserSignService;
import com.ex.mini.user.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /*
        로그아웃
     */
    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request) {
        // 요청에 담긴 세션ID에 해당하는 세션이 있으면 그 세션 반환
        // 없으면 생성X, null반환
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "success";
    }
}
