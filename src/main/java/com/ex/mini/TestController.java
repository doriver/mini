package com.ex.mini;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/12")
    @ResponseBody
    public String aa(@RequestParam("var") String ss) {
        if (ss.equals("ex")) {
            throw new ExpectedException(ErrorCode.FAIL_ITEM_COUNT_DOWN_ORDER);
        }
        return "hello";
    }

    @RequestMapping("/v12")
    public String ab() {
        return "post/test";
    }

}
