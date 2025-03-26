package com.ex.mini.post.domain.repository;

import com.ex.mini.post.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
