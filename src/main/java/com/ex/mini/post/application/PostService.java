package com.ex.mini.post.application;

import com.ex.mini.post.domain.model.Post;
import com.ex.mini.post.domain.repository.PostRepository;
import com.ex.mini.post.presentation.dto.PostCreateDTO;
import com.ex.mini.user.domain.model.User;
import com.ex.mini.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /*
        글 저장
     */
    public void savePost(PostCreateDTO postCreateDTO) {

        User user = userRepository.findById(postCreateDTO.getUserId()).orElse(null);

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
     */
    public void removePost() {

    }
}
