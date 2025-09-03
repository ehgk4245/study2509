package com.back.domain.post.postComment.service;

import com.back.domain.post.postComment.dto.PostCommentResponseDto;
import com.back.domain.post.postComment.entity.PostComment;
import com.back.domain.post.postComment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;


    public List<PostCommentResponseDto> getList(Long id) {
        List<PostComment> comments = postCommentRepository.findByPost_Id(id);

        return comments.stream()
                .map(PostCommentResponseDto::from)
                .toList();
    }
}
