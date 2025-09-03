package com.back.domain.post.postComment.dto;

import com.back.domain.post.postComment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentResponseDto(
        Long id,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static PostCommentResponseDto from(PostComment postComment) {
        return new PostCommentResponseDto(
                postComment.getId(),
                postComment.getContent(),
                postComment.getCreateDate(),
                postComment.getModifyDate()
        );
    }
}
