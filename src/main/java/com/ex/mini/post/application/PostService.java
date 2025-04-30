package com.ex.mini.post.application;

import com.ex.mini.post.domain.entity.Post;
import com.ex.mini.post.domain.repository.PostRepository;
import com.ex.mini.post.presentation.dto.request.PostCreateDTO;
import com.ex.mini.post.presentation.dto.request.PostEditDTO;
import com.ex.mini.post.presentation.dto.response.PostDetailDTO;
import com.ex.mini.post.presentation.dto.response.PostLineDTO;
import com.ex.mini.user.domain.entity.User;
import com.ex.mini.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /*
        글 저장
     */
    public Long savePost(PostCreateDTO postCreateDTO, Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        Post post = Post.builder()
                .writer(user).title(postCreateDTO.getTitle()).content(postCreateDTO.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
    /*
        글들( 제목, 글쓴이, 작성시간 ... ) 가져오기

        todo
         일단 컬럼 다 가져오는거로 했는데, 필요한 컬럼만 가져오도록 변경해야함
     */
    public List<PostLineDTO> getPostList(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 3);
        Page<Post> postPage = postRepository.findAll(pageable);

        List<Post> postList = postPage.getContent();

        List<PostLineDTO> postLineList = new ArrayList<>();
        for (Post post : postList) {
            PostLineDTO postLineDTO = PostLineDTO.builder()
                    .postId(post.getId()).writer(post.getWriter().getNickname())
                    .title(post.getTitle()).createdAt(post.getCreatedAt())
                    .build();
            postLineList.add(postLineDTO);
        }

        return postLineList;
    }

    /*
        글 가져오기
        todo
         댓글등 다른 데이터들 join으로 가져와야함
     */
    public PostDetailDTO getPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        PostDetailDTO postDetailDTO = PostDetailDTO.builder()
                .postId(postId).writer(post.getWriter().getNickname()).title(post.getTitle())
                .content(post.getContent()).createdAt(post.getCreatedAt()).updatedAt(post.getUpdatedAt())
                .build();
        return postDetailDTO;
    }

    /*
        글 수정하기
    */
    public void updatePost(Long postId, PostEditDTO postEditDTO, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);

        if (post.getWriter().getId() == userId) {
            post.editTitle(postEditDTO.getTitle());
            post.editContent(postEditDTO.getContent());
            post.stampUpdatedAt(LocalDateTime.now());
        }

        postRepository.save(post);
    }
    
    
    /*
        글 삭제하기
     */
    public void removePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);

        if (post.getWriter().getId() == userId) {
            postRepository.delete(post);
        }
    }
}
