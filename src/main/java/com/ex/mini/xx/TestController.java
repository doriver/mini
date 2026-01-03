package com.ex.mini.xx;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.Expected4xxException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/12")
    @ResponseBody
    public String aa(@RequestParam("var") String ss) {
        if (ss.equals("ex")) {
            throw new Expected4xxException(ErrorCode.FAIL_ITEM_COUNT_DOWN_ORDER);
        }
        return "hello";
    }

    @ResponseBody
    @PostMapping("/v12")
    public String ab(@Valid @RequestBody ValidationDTO dto) {
        System.out.println(dto.getAge());
        System.out.println(dto.getName());
        System.out.println(dto.getEmail());
        return "데이터 잘 담겼어야함"; // 통과
    }

}
