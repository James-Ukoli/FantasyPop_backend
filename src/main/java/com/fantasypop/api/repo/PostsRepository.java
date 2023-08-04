package com.fantasypop.api.repo;

import com.fantasypop.api.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface PostsRepository extends JpaRepository<Posts, String> {
}
