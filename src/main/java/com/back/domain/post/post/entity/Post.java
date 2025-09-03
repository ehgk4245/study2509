package com.back.domain.post.post.entity;

import com.back.domain.post.postComment.entity.PostComment;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(String content) {
        PostComment postComment = PostComment.builder()
                .content(content)
                .post(this)
                .build();
        comments.add(postComment);
        return postComment;
    }

    public PostComment findCommentById(Long id) {
        return comments.stream()
                .filter(postComment -> Objects.equals(postComment.getId(), id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("%d번 comment가 존재하지 않습니다".formatted(id)));
    }

    public boolean deleteComment(PostComment postComment) {
        if (postComment == null) return false;

        return comments.remove(postComment);
    }
}
