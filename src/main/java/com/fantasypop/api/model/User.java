package main.java.com.fantasypop.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "users")
public class User {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, name="first_name")
    private String firstname;
    @Column (nullable = false, name="last_name")
    private String lastname;
@Column (nullable = false, name="email", unique = true)
    private String email;
@Column (nullable = false, name = "username", unique = true)
    private String username;
@Column(nullable = false, name="password")
    private String password;
@Column(nullable = false, name = "birthday")
    private LocalDate birthday;
@Column(name = "profilePic_url")
    private String profilePic;
    //SocialMedia Links will be **NOT BE REQUIRED** for users
    @Column(name = "social_media")
    private Map<String, String> socialMediaLinks;

//Constructor
    public User(int id, String firstname, String lastname, String email, String username, String password, LocalDate birthday, String profilePic, Map<String, String> socialMediaLinks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.profilePic = profilePic;
        this.socialMediaLinks = socialMediaLinks;
    }

    //Methods
    public int getID() {
        return id;
    }
    public void setID(int id) {
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
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public LocalDate getBirthday(){
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getProfilePic(){
        return profilePic;
    }
    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }
    public Map<String, String> getSocialMediaLinks(){
        return socialMediaLinks;
    }
    public void setSocialMediaLinks(Map<String,String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }

}
