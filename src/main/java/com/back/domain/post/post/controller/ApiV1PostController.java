package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.request.PostWriteReq;
import com.back.domain.post.post.dto.response.PostRes;
import com.back.domain.post.post.service.PostService;
import com.back.global.apiResponse.ApiResponse;
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
    public List<PostRes> getPosts() {
        return postService.getCommentsInPost();
    }

    @GetMapping("{id}")
    public PostRes getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ApiResponse.success(200, "삭제 성공");
    }

    @PostMapping
    public ApiResponse<Void> createPost(@Valid @RequestBody PostWriteReq postWriteReq) {
        postService.create(postWriteReq);
        return ApiResponse.success(200, "생성 성공");
    }
}
