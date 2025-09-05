package com.back.domain.post.post.service;

import com.back.domain.post.post.dto.request.PostWriteRequest;
import com.back.domain.post.post.dto.response.PostResponse;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import com.back.domain.post.postComment.dto.PostCommentResponse;
import com.back.domain.post.postComment.dto.PostCommentUpdateRequest;
import com.back.domain.post.postComment.entity.PostComment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    @Transactional
    public Post create(PostWriteRequest postWriteRequest) {
        Post post = postWriteRequest.toEntity();
        return postRepository.save(post);
    }

    public List<PostResponse> getCommentsInPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional
    public void createComment(Post post, String content) {
        post.addComment(content);
    }

    public List<PostCommentResponse> getCommentsInPost(Long postId) {
        Post post = getPostOrThrow(postId);
        List<PostComment> comments = post.getComments();

        return comments.stream()
                .map(PostCommentResponse::from)
                .toList();
    }

    public PostCommentResponse getCommentInPost(Long postId, Long commentId) {
        Post post = getPostOrThrow(postId);
        PostComment comment = post.findCommentById(commentId);
        return PostCommentResponse.from(comment);
    }

    @Transactional
    public boolean deleteCommentInPost(Long postId, Long commentId) {
        Post post = getPostOrThrow(postId);
        PostComment comment = post.findCommentById(commentId);
        post.deleteComment(comment);
        return true;
    }

    @Transactional
    public boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    public PostResponse getPost(Long id) {
        Post post = getPostOrThrow(id);
        return PostResponse.from(post);
    }

    @Transactional
    public void updateCommentInPost(Long postId, Long commentId, PostCommentUpdateRequest postCommentUpdateRequest) {
        Post post = getPostOrThrow(postId);
        PostComment comment = post.findCommentById(commentId);
        comment.modify(postCommentUpdateRequest.content());
    }

    private Post getPostOrThrow(Long postId) {
        return postRepository.findByIdWithComments(postId).orElseThrow(
                () -> new EntityNotFoundException("게시글이 존재하지 않습니다.")
        );
    }
}
