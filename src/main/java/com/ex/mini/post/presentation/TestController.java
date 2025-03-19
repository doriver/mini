package com.ex.mini.post.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/12")
    @ResponseBody
    public String aa() {
        return "hello";
    }

    @RequestMapping("/v12")
    public String ab() {
        return "post/test";
    }

}
