package com.back.domain.post.post.dto.request;

import com.back.domain.post.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostWriteRequest(
        @NotBlank @Size(min = 2, max = 100)
        String title,

        @NotBlank
        @Size(min = 2, max = 100)
        String content) {

        public Post toEntity() {
                return Post.builder()
                        .title(title)
                        .content(content)
                        .build();
        }
}
