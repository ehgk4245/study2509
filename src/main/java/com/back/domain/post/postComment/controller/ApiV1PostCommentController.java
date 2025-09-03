package com.back.domain.post.postComment.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import com.back.domain.post.postComment.dto.PostCommentResponseDto;
import com.back.domain.post.postComment.entity.PostComment;
import com.back.domain.post.postComment.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class ApiV1PostCommentController {

    private final PostCommentService postCommentService;
    private final PostService postService;

    @GetMapping
    public List<PostCommentResponseDto> getItems(@PathVariable("postId") Long postId) {
        return postCommentService.getList(postId);
    }

    @GetMapping("/{id}")
    public PostCommentResponseDto getItem(@PathVariable("postId") Long postId,
                                          @PathVariable("id") Long id) {
        Post post = postService.getPost(postId);
        PostComment comment = post.findCommentById(id);
        return PostCommentResponseDto.from(comment);
    }
}
