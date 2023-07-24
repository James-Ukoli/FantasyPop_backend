package com.fantasypop.api.controller;

import com.fantasypop.api.model.Post;
import com.fantasypop.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
     private PostService postService;
     @Autowired
     public PostController(PostService, postService) {
         this.postService = postService;
     }

    @PostMapping
    @GetMapping
    @GetMapping
    public Post getPost(@RequestParam Integer id) {
         return postService.getPost(id);
    }
    @Update
    @DeleteMapping
}
