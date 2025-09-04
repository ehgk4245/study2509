package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.PostResponseDto;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponseDto> getItems() {
        return postService.getCommentsInPost();
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "삭제 성공";
    }
}
