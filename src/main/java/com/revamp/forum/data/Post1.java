package com.revamp.forum.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="posts")
// Pops
public class Post1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1024)
    private String content;
    @ManyToOne
    @JsonIgnoreProperties({"posts", "password"})
    private User author;
    @Column(name="likes")
    private int likes;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"posts"})
    private Category category;
}