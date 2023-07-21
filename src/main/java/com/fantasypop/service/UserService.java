package com.fantasypop.service;

import com.fantasypop.api.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;


@Service
public class UserService {

    private List<User> users;

    public UserService() {
        // Dummy data initialization (4 users)
        users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john@example.com", "John123", "john321", "1990-08-15", null, null ));
        users.add(new User(2L, "Jane", "Smith", "jane@example.com", "Jane123", "jane321", "1992-03-24", null, null));
        users.add(new User(3L, "Bob", "Johnson", "bob@example.com", "Bob123", "bob321", "2001-12-12", null, null));
        users.add(new User(4L, "Alice", "Green", "alice@example.com", "Alice123", "alice321", "1984-02-27", null, null));
    }

    // READ METHODS
    public User getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
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

    public List<User> getActiveUsers() {
        return users.stream()
                .filter(User::isActive)
                .toList();
    }
// LIST METHOD
    public List<User> getUsers() {
        return users;
    }

    /// CREATE METHOD
    public User createUser(String username, String email, boolean isActive) {
        Long newUserId = users.stream().mapToLong(User::getUserId).max().orElse(0) + 1;
        User newUser = new User(newUserId, username, email, isActive);
        users.add(newUser);
        return newUser;
    }

    // UPDATE METHOD
    public User updateUser(Long userId, String username, String email, boolean isActive) {
        User userToUpdate = getUserById(userId);
        if (userToUpdate != null) {
            userToUpdate.setUsername(username);
            userToUpdate.setEmail(email);
            userToUpdate.setActive(isActive);
            return userToUpdate;
        }
        return null;
    }
    /// DELETE METHOD
    public boolean deleteUser(Long userId) {
        User userToDelete = getUserById(userId);
        if (userToDelete != null) {
            users.remove(userToDelete);
            return true;
        }
        return false;