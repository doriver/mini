package com.ex.mini.post.presentation.api;

import com.ex.mini.post.application.PostService;
import com.ex.mini.post.presentation.dto.PostCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /*
        글 작성 api
     */
    public String createPost(@RequestBody PostCreateDTO postCreateDTO) {
        postService.savePost(postCreateDTO);
        return "success";
    }

}
