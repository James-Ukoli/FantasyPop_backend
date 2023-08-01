package com.fantasypop.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data // needed?
@Table(name = "users") // Warning underlines will be removed once database is correctly connected
public class Users {
    // Default constructor
    ///Fields
    @Id //1
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, name="first_name") // 2
    private String firstname;
    @Column (nullable = false, name="last_name") // 3
    private String lastname;
@Column (nullable = false, name="email", unique = true) // 4
    private String email;
@Column (nullable = false, name = "username", unique = true) // 5
    private String username;
@Column(nullable = false, name="password") // 6
    private String password;

@Column(nullable = false, name = "date_of_birth") // 7
    private String dob;
@Column(name = "profilePic_url") // 8
    private String profilePic;
    //SocialMedia Links will be **NOT BE REQUIRED** for users
    @Column(name = "social_media") //9
    @ElementCollection
    private Set<String> socialMediaLinks = new HashSet<>();

}
