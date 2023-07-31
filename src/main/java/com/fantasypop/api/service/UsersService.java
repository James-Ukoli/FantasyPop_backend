package com.fantasypop.api.service;

import com.fantasypop.api.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UsersService {
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private List<Users> users;

    public UsersService() {
        // Dummy data initialization (4 users)
        users = new ArrayList<>();
        users.add(new Users(1L, "John", "Doe", "john@example.com", "John123", "john321", "1990-08-15", null, null));
        users.add(new Users(2L, "Jane", "Smith", "jane@example.com", "Jane123", "123jane", "1992-03-24", null, null));
        users.add(new Users(3L, "Bob", "Johnson", "bob@example.com", "Bob123", "bob321", "2001-12-12", null, null));
        users.add(new Users(4L, "Alice", "Green", "alice@example.com", "Alice123",  "alice321", "1984-02-27", null, null));
    }
    // LIST METHOD
    public List<Users> getUsers() {
        return users;
    }
    // READ METHODS (id, username, email) *ONLY UNIQUE FIELDS*
    public Users getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public Users getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public Users getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    /// CREATE METHOD
    public Users registerUser(String firstname, String lastname, String email, String username, String password, String dob, String profilePic, Set<String> socialMediaLinks) {
        Long newUserId = users.stream().mapToLong(Users::getId).max().orElse(0) + 1; // add id to user
        Users newUser = new Users(newUserId, firstname, lastname, email, username, password, dob, profilePic, socialMediaLinks );
        users.add(newUser);
        return newUser;
    }

    public boolean verifyUserLogin(String username, String password){
    Users user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }


    // UPDATE METHOD
    public Users updateUser(Long id, String firstname, String lastname, String email, String username, String password, String dob, String profilePic, Set<String> socialMediaLinks) {
        Users userToUpdate = getUserById(id);
        if (userToUpdate != null) {
            userToUpdate.setFirstname(firstname);
            userToUpdate.setLastname(lastname);
            userToUpdate.setEmail(email);
            userToUpdate.setUsername(username);
            userToUpdate.setPassword(password);
            userToUpdate.setDob(dob);
            userToUpdate.setProfilePic(profilePic);
            userToUpdate.setSocialMediaLinks(socialMediaLinks);
            return userToUpdate;
        }
        return null;
    }

    /// DELETE METHOD
    public boolean deleteUser(Long id) {
        Users userToDelete = getUserById(id);
        if (userToDelete != null) {
            users.remove(userToDelete);
            return true;
        }
        return false;
    }
}