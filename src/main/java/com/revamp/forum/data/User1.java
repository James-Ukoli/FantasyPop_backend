package com.revamp.forum.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

// THIS IS RUNNING ON 8/28/23 //
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users1")
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // SIGNUP DATA POINT 1
    @Column(nullable = false, unique = true, length = 100)
    private String userName;
    // SIGNUP DATA POINT 2
    @Column(nullable=false, length= 256)
    @Transient
    private String password;
    // SIGNUP DATA POINT 3
    @Column(nullable = false, unique = true, length = 256)
    private String email;
    // SIGNUP DATA POINT 4
    @Column(nullable = false)
    private LocalDate birthdate;
    @Column(nullable = true)
    @Transient
    private String profileImage;
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate updatedAt;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private Collection<Post> posts;
    // Column
}