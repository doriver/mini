package com.ex.mini.post.presentation.view;

import com.ex.mini.post.application.PostService;
import com.ex.mini.post.domain.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/view/post")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;

    /*
        게시글 목록 화면
        해당 페이지에 맞는 게시글들을 html에 담는다.
     */
    @GetMapping("/list")
    public String postListView(@RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber, Model model) {
        List<Post> postList = postService.getPostList(pageNumber);
        model.addAttribute("postList", postList);
        return "post/list";
    }

    @GetMapping("/add")
    public String postAddView() {
        return "post/add";
    }

    @GetMapping("/detail")
    public String postDetailView() {
        return "post/detail";
    }

    @GetMapping("/edit")
    public String postEditView() {
        return "post/edit";
    }

}
