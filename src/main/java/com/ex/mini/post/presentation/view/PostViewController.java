package com.ex.mini.post.presentation.view;

import com.ex.mini.post.application.PostService;
import com.ex.mini.post.domain.model.Post;
import com.ex.mini.post.presentation.dto.response.PostDetailDTO;
import com.ex.mini.post.presentation.dto.response.PostLineDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/view/posts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;

    /*
        게시글 목록 화면
        해당 페이지에 맞는 게시글들을 html에 담는다.
     */
    @GetMapping("/list")
    public String postListView(Model model
        , @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber) {
        List<PostLineDTO> postLineList = postService.getPostList(pageNumber);
        model.addAttribute("postLineList", postLineList);
        return "post/list";
    }

    /*
        글 작성 화면
        
        todo
         작성하던데이터 가지고 있다가 넘겨줄수도
     */
    @GetMapping("/add")
    public String postAddView() {
        return "post/add";
    }

    /*
        글 상세 화면
     */
    @GetMapping("/{postId}/detail")
    public String postDetailView(Model model, @PathVariable("postId") Long postId) {
        PostDetailDTO postDetailDTO = postService.getPost(postId);
        model.addAttribute("postDetail", postDetailDTO);
        return "post/detail";
    }

    /*
        글 수정 화면
     */
    @GetMapping("/{postId}/edit")
    public String postEditView(Model model, @PathVariable("postId") Long postId) {
        PostDetailDTO postDetailDTO = postService.getPost(postId);
        model.addAttribute("postDetail", postDetailDTO);
        return "post/edit";
    }

}
