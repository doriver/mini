package com.ex.mini.user.presentation.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/user")
public class SignViewController {

    @GetMapping("/sign")
    public String signView() {
        return "user/sign";
    }
}
