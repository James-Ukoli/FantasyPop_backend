package com.fantasypop.api.controller;

import com.fantasypop.api.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    /// CRUD

   // Read Post

    // List all posts by Category

    // List all post



}
