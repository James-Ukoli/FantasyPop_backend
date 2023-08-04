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
public class Posts {
    //Fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, name = "userId")
    private int userId; // can be anonymous
    @NotBlank
    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "vote_difference") // Positive to Negative Ratio for post
    private int voteDifference;
    @Column(name ="total_votes")
    private int totalVotes;
    @Column(nullable = false, name = "timestamp")
    private LocalDateTime timestamp;
    @ElementCollection
    @Column(name="key_players")
    private Map<String, String> keyPlayers;
    @Column(nullable = false, name="sport")
    private String sport;

    @Column(nullable = false, name="topic")
    private String topic;
    /// 8-1-23 Comments, FlagCount, has been converted to a Class
}
