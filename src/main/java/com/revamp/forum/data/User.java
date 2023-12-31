package com.revamp.forum.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 100)
    private String userName;
    @Column(nullable = false, unique = true, length = 256)
    private String email;
    @Column(nullable = false)
    private LocalDate createdAt;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private Collection<Post> posts;
    @Transient
    private String profilePic;
    // Column
}