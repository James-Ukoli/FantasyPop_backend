package com.fantasypop.api.model;
import jakarta.persistence.*;
import lombok.*;


import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data // needed?
@Table(name = "posts")
public class Post {
    //Fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, name = "username")
    private String username; // can be anonymous

    @Column(nullable = false, name = "content")
    private String content;

    @Column(name = "vote_ratio") // Positive to Negative Ratio for post
    private int voteRatio;
    @Column(name ="total_votes")
    private int totalVotes;
    @Column(nullable = false, name = "timestamp")
    private LocalDateTime timestamp;

    @ElementCollection
    @Column(name="key_players")
    private Map<String, String> keyPlayers;

    @Column(nullable = false, name="sport")
    private String sport;

    @Column(name="flag_count")
    private int flagCount;
    @Column(nullable = false, name="topic")
    private String topic;

    @Column(nullable = false, name = "comments")
    private String comments;
}
