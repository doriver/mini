package com.ex.mini.post.presentation.api;

import com.ex.mini.post.application.PostService;
import com.ex.mini.post.presentation.dto.request.PostCreateDTO;
import com.ex.mini.post.presentation.dto.request.PostEditDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /*
        글 작성 api
        글 작성화면 - 등록하기 버튼
     */
    @PostMapping
    public Map<String,Object> createPost(@RequestBody PostCreateDTO postCreateDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // 현재 요청한 사용자
        Long savedPostId = postService.savePost(postCreateDTO, userId);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result","success");
        resultMap.put("postId",savedPostId);

        return resultMap;
    }

    /*
        글 수정 api
    */
    @PatchMapping("/{id}")
    public String editPost(@PathVariable("id") Long postId
            ,@RequestBody PostEditDTO postEditDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        postService.updatePost(postId, postEditDTO, userId);

        return "success";
    }

    /*
        글 삭제 api
        글 상세화면 - 삭제버튼
     */
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Long postId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        postService.removePost(postId, userId);
        return "success";
    }



}
