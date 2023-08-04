package com.fantasypop.api.service;


import com.fantasypop.api.model.Posts;
import com.fantasypop.api.repo.PostsRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostsService {

    private List<Posts> posts;
    private PostsRepository postsRepository;

    // List Post
    public List<Posts> getPosts() {
        return posts;
    }

    // Read Single Post
    public Posts getPostById(Long postId) {
        return null;
    }


    // List all posts by sport by topic and by highest total votes (Sport AND Topic filter and Order by total_votes)
    public Posts getPostsBySportAndTopicAndTotalVotes(String sport, String topic, int totalVotes) {
         System.out.println("This is on the news feed page");
        return null;
    }

    /// List all posts by Sport, by Topic, and by Recent timestamp (Sport and Topic filter and recently git Order timestamp Order)
    public Posts getPostsBySportAndTopicAndRecentTimeStamp(String sport, String topic, LocalDateTime timestamp){
        System.out.println("This is on the news feed page");
        return null;
    }

    // List all posts by userId and recent time stamps (Profile page)
    public Posts getPostsByUserIdAndRecentTimestamps(){
        System.out.println("This is on the profile page");
        return null;
    }

    /// Create Post

    // NO UPDATING Post in Application

    // Delete Posts
    public void deletePost(Long id) {
        Posts postToDelete = getPostById(id);
        if (postToDelete != null) {
            posts.remove(postToDelete);
        }
    }
}
