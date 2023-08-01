package com.fantasypop.api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;


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
    @NotBlank
    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "vote_sum_ratio") // Positive to Negative Ratio for post
    private int voteSumRatio;
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
