package com.back.domain.post.postComment.controller;

import com.back.domain.post.post.service.PostService;
import com.back.domain.post.postComment.dto.PostCommentResponseDto;
import com.back.global.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class ApiV1PostCommentController {

    private final PostService postService;

    @GetMapping
    public List<PostCommentResponseDto> getComments(@PathVariable("postId") Long postId) {
        return postService.getCommentsInPost(postId);
    }

    @GetMapping("/{commentId}")
    public PostCommentResponseDto getComment(@PathVariable("postId") Long postId,
                                          @PathVariable("commentId") Long commentId) {
        return postService.getCommentInPost(postId, commentId);
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable("postId") Long postId,
                                     @PathVariable("commentId") Long commentId) {
        postService.deleteCommentInPost(postId, commentId);
        return ApiResponse.success(200, "삭제 성공");
    }
}
