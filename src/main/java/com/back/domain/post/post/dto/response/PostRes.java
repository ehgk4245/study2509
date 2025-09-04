package com.back.domain.post.post.dto.response;

import com.back.domain.post.post.entity.Post;

import java.time.LocalDateTime;

public record PostRes(
        Long id,
        String subject,
        String body,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static PostRes from(Post post) {
        return new PostRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreateDate(),
                post.getModifyDate()
        );
    }
}