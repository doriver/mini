package com.ex.mini.post.presentation.api;

import com.ex.mini.post.application.PostService;
import com.ex.mini.post.presentation.dto.PostCreateDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /*
        글 작성 api
     */
    @PostMapping
    public String createPost(@RequestBody PostCreateDTO postCreateDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        postService.savePost(postCreateDTO, userId);
        return "success";
    }

    /*
        글 삭제 api
        postId만 받으면 되나? userId도 받을수도 있겠지
     */
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Long postId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        postService.removePost(postId, userId);
        return "succsess";
    }

}
