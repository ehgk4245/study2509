package com.back.domain.post.postComment.repository;

import com.back.domain.post.postComment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPost_Id(Long postId);
}
