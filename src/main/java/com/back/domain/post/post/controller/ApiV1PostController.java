package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.request.PostWriteRequest;
import com.back.domain.post.post.dto.response.PostResponse;
import com.back.domain.post.post.service.PostService;
import com.back.global.apiResponse.ApiResponse;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getCommentsInPost();
    }

    @GetMapping("{id}")
    public PostResponse getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ApiResponse.success(200, "삭제 성공");
    }

    @PostMapping
    public ApiResponse<Void> createPost(@Valid @RequestBody PostWriteRequest postWriteRequest) {
        postService.create(postWriteRequest);
        return ApiResponse.success(200, "생성 성공");
    }
}
