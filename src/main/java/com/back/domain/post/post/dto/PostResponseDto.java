package com.back.domain.post.post.dto;

import com.back.domain.post.post.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostResponseDto {
    private Long id;
    private String subject;
    private String body;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .subject(post.getTitle())
                .body(post.getContent())
                .createdDate(post.getCreateDate())
                .modifiedDate(post.getModifyDate())
                .build();
    }
}
