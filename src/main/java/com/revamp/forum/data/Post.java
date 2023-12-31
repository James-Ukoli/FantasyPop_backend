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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1024)
    private String content;
    @ManyToOne
    @JsonIgnoreProperties({"posts", "password"})
    private User author;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"posts"})
    private Category category;
}