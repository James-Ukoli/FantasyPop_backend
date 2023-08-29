package com.revamp.forum.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

// THIS WAS ADDED 8/28/23 @22:30pm //
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="posts") // Pops
public class Post1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private String sport;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false, length = 1024)
    private String content;
    @Column(name="total_votes")
    private int totalVotes;
    @Column(name="up_votes")
    private int upVotes;
    @Column(name="down_votes")
    private int downVotes;
    @Column(name="vote_difference")
    private int voteDifference;
    @Column(nullable = false)
    private LocalDate createdAt;
    @ManyToOne
    @JsonIgnoreProperties({"posts", "password"})
    private User author;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"posts"})
    private Category category;
}