package com.back.global.initData;

import com.back.domain.post.post.dto.request.PostWriteReq;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;

    private final PostService postService;

    @Bean
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) return;

        Post post1 = postService.create(new PostWriteReq("제목 1", "내용 1"));
        Post post2 = postService.create(new PostWriteReq("제목 2", "내용 2"));
        Post post3 = postService.create(new PostWriteReq("제목 3", "내용 3"));

        postService.createComment(post1, "댓글 1 - 1");
        postService.createComment(post1, "댓글 1 - 2");
        postService.createComment(post1, "댓글 1 - 3");
        postService.createComment(post2, "댓글 2 - 1");
        postService.createComment(post2, "댓글 2 - 2");
    }
}
