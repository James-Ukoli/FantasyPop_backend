
package com.fantasypop.api.controller;
import com.fantasypop.api.model.User;
import com.fantasypop.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
private List<User> users = new ArrayList<>();

@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
UserService userService;

  
@GetMapping /// list
 public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping /// read
    public User getUserById(@PathVariable Long id){
        User user = users.stream().filter(u -> u.getID().equals(id)).findFirst().orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    // PASSWORD TEMPLATE

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Long id = System.currentTimeMillis();
        user.setID(id);

        // Hash the password using BCrypt before storing it
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Save the user in the data store
        users.add(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        boolean loginSuccess = userService.verifyUserLogin(loginUser);
        if (loginSuccess) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }




 
}
