package com.back.domain.post.post.dto.response;

import com.back.domain.post.post.entity.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String subject,
        String body,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreateDate(),
                post.getModifyDate()
        );
    }
}