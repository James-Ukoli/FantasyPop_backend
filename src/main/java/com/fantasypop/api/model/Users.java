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
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, name="first_name")
    private String firstname;
    @Column (nullable = false, name="last_name")
    private String lastname;
@Column (nullable = false, name="email", unique = true)
    private String email;
@Column (nullable = false, name = "username", unique = true)
    private String username;
@Column(nullable = false, name="password") // Need to be private
    private String password;

@Column(nullable = false, name = "date_of_birth")
    private String dob;
@Column(name = "profilePic_url")
    private String profilePic;
    //SocialMedia Links will be **NOT BE REQUIRED** for users
    @Column(name = "social_media")
    @ElementCollection
    private Set<String> socialMediaLinks = new HashSet<>();

}
