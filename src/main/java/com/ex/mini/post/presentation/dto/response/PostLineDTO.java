package com.ex.mini.post.presentation.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLineDTO {
    private Long postId;
    private String writer;
    private String title;
    private LocalDateTime createdAt;
}
