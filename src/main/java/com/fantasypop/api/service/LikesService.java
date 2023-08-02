package com.fantasypop.api.service;

import com.fantasypop.api.model.Likes;
import com.fantasypop.api.repo.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
    private LikesRepository likesRepository;

    @Autowired
    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public Likes likePost(Likes like) {
        // Add any validation or business logic here before saving the like
        return likesRepository.save(like);
    }

    public void unlikePost(Long likeId) {
        // Add any validation or business logic here before deleting the like
        likesRepository.deleteById(likeId);
    }
}
