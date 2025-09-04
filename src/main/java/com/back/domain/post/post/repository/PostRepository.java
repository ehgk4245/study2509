package com.back.domain.post.post.repository;

import com.back.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.comments where p.id = :id")
    Optional<Post> findByIdWithComments(@Param("id") Long id);
}
