package com.ex.mini.post.application;

import com.ex.mini.post.domain.model.Post;
import com.ex.mini.post.domain.repository.PostRepository;
import com.ex.mini.post.presentation.dto.PostCreateDTO;
import com.ex.mini.user.domain.model.User;
import com.ex.mini.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     */
    public void getPostList() {

    }

    /*
        글 가져오기
     */
    public void getPost() {

    }

    /*
        글 삭제하기
        글을 조회하고, 거기서 userId값이 같을때 삭제?
     */
    public void removePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);

        if (post.getWriter().getId() == userId) {
            postRepository.delete(post);
        }

    }
}
