package com.fantasypop.api.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


@Entity
@Table(name = "users") // Warning underlines will be removed once database is correctly connected
public class User {
    // Default constructor
    public User () {

    }

    ///Fields
    @Id
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
@Column(nullable = false, name="passwordHash") // Need to be private
    private String passwordHash;
    @Column(nullable = false, name="passwordSalt") // Need to be private
    private String passwordSalt;
@Column(nullable = false, name = "date_of_birth")
    private String dob;
@Column(name = "profilePic_url")
    private String profilePic;
    //SocialMedia Links will be **NOT BE REQUIRED** for users
    @Column(name = "social_media")
    @ElementCollection
    private Set<String> socialMediaLinks = new HashSet<>();


    //Constructor
    public User(Long id, String firstname, String lastname, String email, String username, String passwordHash, String passwordSalt, String dob, String profilePic, Set<String> socialMediaLinks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.dob = dob;
        this.profilePic = profilePic;
        this.socialMediaLinks = socialMediaLinks;
    }

    //Methods
    public Long getID() {
        return id;
    }
    public void setID(Long id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash= passwordHash;
    }
    public String getPasswordSalt(){
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt){
        this.passwordSalt= passwordSalt;
    }
    public String getDob(){
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getProfilePic(){
        return profilePic;
    }
    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }
    public Set<String> getSocialMediaLinks(){
        return socialMediaLinks;
    }
    public void setSocialMediaLinks(Set<String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }

    //PASSWORD TEMPLATE
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
        // Additional methods for other queries
    }


}
