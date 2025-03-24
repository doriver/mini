package com.ex.mini.post.presentation.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/post")
public class PostViewController {

    @GetMapping("/list")
    public String postListView() {
        return "post/list";
    }

    @GetMapping("/add")
    public String addPostView() {
        return "post/add";
    }
}
