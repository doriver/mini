package com.ex.mini.post.application;

import com.ex.mini.post.domain.model.Post;
import com.ex.mini.post.domain.repository.PostRepository;
import com.ex.mini.post.presentation.dto.PostCreateDTO;
import com.ex.mini.user.domain.model.User;
import com.ex.mini.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /*
        글 저장
     */
    public void savePost(PostCreateDTO postCreateDTO, Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        Post post = Post.builder()
                .writer(user).title(postCreateDTO.getTitle()).content(postCreateDTO.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        postRepository.save(post);
    }
    /*
        글들( 제목, 글쓴이, 작성시간 ... ) 가져오기

        todo
         일단 컬럼 다 가져오는거로 했는데, 필요한 컬럼만 가져오도록 변경해야함
     */
    public List<Post> getPostList(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Post> postPage = postRepository.findAll(pageable);

        List<Post> postList = postPage.getContent();
        return postList;
    }

    /*
        글 가져오기
     */
    public void getPost() {

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
