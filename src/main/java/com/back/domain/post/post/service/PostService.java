package com.back.domain.post.post.service;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
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
    public Long create(String title, String content) {
        return postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build()).getId();
    }

    @Transactional
    public void update(Post post, String title, String content) {
        post.modify(title, content);
    }

    public List<Post> getList() {
        return postRepository.findAll();
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("게시글이 존재하지 않습니다.")
        );
    }
}
