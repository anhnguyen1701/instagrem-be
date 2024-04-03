package com.uet.instagrem.modules.post.service.controllers;

import com.uet.instagrem.common.mongodb.entities.PostEntity;
import com.uet.instagrem.modules.post.service.dtos.PostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uet.instagrem.common.mongodb.repositories.PostRepository;

@Slf4j
@RestController
@RequestMapping(value = "/posts", produces = "application/vnd.api.v1+json")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public ResponseEntity<?> findAllPosts() {
        return ResponseEntity.ok("work!");
    }

    @PostMapping
    ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        log.info(postRequest.toString());
        postRepository.insert(PostEntity.builder().content(postRequest.content).createdBy(postRequest.created).build());
        return ResponseEntity.ok("tao tahn hcong");
    }
}
