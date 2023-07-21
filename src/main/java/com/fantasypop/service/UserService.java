package com.fantasypop.service;

import com.fantasypop.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


@Service
public class UserService {


    private List<User> users;

    public UserService() {
        // Dummy data initialization (4 users)
        users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", "John123", "john321", "1990-08-15", null, null));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", "Jane123", "jane321", "1992-03-24", null, null));
        users.add(new User(3L, "Bob", "Johnson", "bob@example.com", "Bob123", "bob321", "2001-12-12", null, null));
        users.add(new User(4L, "Alice", "Green", "alice@example.com", "Alice123", "alice321", "1984-02-27", null, null));
    }
    // LIST METHOD
    public List<User> getUsers() {
        return users;
    }
    // READ METHODS (id, username, email) *ONLY UNIQUE FIELDS*
    public User getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getID().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    /// CREATE METHOD
    public User createUser(String firstname, String lastname, String email, String username, String password, String dob, String profilePic, Map<String, String> socialMediaLinks) {
        Long newUserId = users.stream().mapToLong(User::getID).max().orElse(0) + 1; // add id to user
        User newUser = new User(newUserId, firstname, lastname, email, username, password, dob, profilePic, socialMediaLinks );
        users.add(newUser);
        return newUser;
    }

    // UPDATE METHOD
    public User registerUser(Long id, String firstname, String lastname, String email, String username, String password, String dob, String profilePic, Map<String, String> socialMediaLinks) {
        User userToUpdate = getUserById(id);
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
        User userToDelete = getUserById(id);
        if (userToDelete != null) {
            users.remove(userToDelete);
            return true;
        }
        return false;
    }
    // PASSWORD HASHING TEMPLATE
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        String rawPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        User user = new User();
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(hashedPassword);

        user.save(user);
    }

    public boolean verifyUserLogin(User  user) {
        User user = user.findByUsername(user.getUsername());

        if (user == null) {
            return false; // User not found
        }

        String rawPassword = user.getPassword();
        String storedHashedPassword = user.getPassword();

        return passwordEncoder.matches(rawPassword, storedHashedPassword);
    }
}