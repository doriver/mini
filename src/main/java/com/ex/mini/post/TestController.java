package com.ex.mini.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
