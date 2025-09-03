package com.back.domain.post.post.service;

import com.back.domain.post.post.dto.PostResponseDto;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
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

    @Transactional
    public void update(Post post, String title, String content) {
        post.modify(title, content);
    }

    public List<PostResponseDto> getList() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponseDto::from)
                .toList();
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("게시글이 존재하지 않습니다.")
        );
    }

    public void createComment(Post post, String content) {
        post.addComment(content);
    }

    public boolean deleteComment(Post post, PostComment postComment) {
        return post.deleteComment(postComment);
    }

    public void modify(PostComment postComment, String content) {
        postComment.modify(content);
    }
}
