package com.fantasypop.api.controller;

import com.fantasypop.api.model.Likes;
import com.fantasypop.api.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {
    private LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping
    public ResponseEntity<Likes> likePost(@RequestBody Likes like) {
        Likes createdLike = likesService.likePost(like);
        return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long likeId) {
        likesService.unlikePost(likeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
