package com.fantasypop.api.repo;

import com.fantasypop.api.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // Custom query methods can be added here if needed
}
