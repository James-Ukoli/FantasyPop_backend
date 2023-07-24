
package com.fantasypop.api.controller;
import com.fantasypop.api.model.User;
import com.fantasypop.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping(path = "api/vi/user")
public class UserController {
//    @Autowired (PASSWORD TEMPLATE)
//    private User.UserRepository userRepository;


 // Constructor injection

    @Autowired
 UserService userService;

  
    @GetMapping /// list
 public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping /// read
    public User getUserById(@RequestParam Long id){
        return userService.getUserById(id);
    }

    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    // PASSWORD TEMPLATE

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User userDTO) {
        // Validate the userDTO and other fields

        // Hash the password before saving it
        String hashedPassword = userService.hashPassword(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        // Save the user with the hashed password
        userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully!");
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
