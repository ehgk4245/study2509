package com.back.domain.post.postComment.dto;

import com.back.domain.post.postComment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentResponse(
        Long id,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static PostCommentResponse from(PostComment postComment) {
        return new PostCommentResponse(
                postComment.getId(),
                postComment.getContent(),
                postComment.getCreateDate(),
                postComment.getModifyDate()
        );
    }
}
