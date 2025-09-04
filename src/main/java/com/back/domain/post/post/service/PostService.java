package com.back.domain.post.post.service;

import com.back.domain.post.post.dto.PostResponseDto;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import com.back.domain.post.postComment.dto.PostCommentResponseDto;
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
    public Post create(String title, String content) {
        return postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());
    }

    public List<PostResponseDto> getCommentsInPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponseDto::from)
                .toList();
    }

    @Transactional
    public void createComment(Post post, String content) {
        post.addComment(content);
    }

    public List<PostCommentResponseDto> getCommentsInPost(Long postId) {
        Post post = getPostOrThrow(postId);
        List<PostComment> comments = post.getComments();

        return comments.stream()
                .map(PostCommentResponseDto::from)
                .toList();
    }

    public PostCommentResponseDto getCommentInPost(Long postId, Long commentId) {
        Post post = getPostOrThrow(postId);
        PostComment comment = post.findCommentById(commentId);
        return PostCommentResponseDto.from(comment);
    }

    @Transactional
    public boolean deleteCommentInPost(Long postId, Long commentId) {
        Post post = getPostOrThrow(postId);
        PostComment comment = post.findCommentById(commentId);
        post.deleteComment(comment);
        return true;
    }

    private Post getPostOrThrow(Long postId) {
        return postRepository.findByIdWithComments(postId).orElseThrow(
                () -> new EntityNotFoundException("게시글이 존재하지 않습니다.")
        );
    }

    @Transactional
    public boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }
}
