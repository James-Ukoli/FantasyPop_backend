
package com.fantasypop.api.controller;
import com.fantasypop.api.model.Users;
import com.fantasypop.api.repo.UsersRepository;
import com.fantasypop.api.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UsersController {

private PasswordEncoder passwordEncoder;

private UsersRepository usersRepository;

@Autowired
UsersService usersService;

  
@GetMapping /// list
 public List<Users> getUsers(){
        return usersService.getUsers();
    }

//    @GetMapping /// read
//    public Users getUserById(@PathVariable Long id){
//        Users user = user.stream().filter(u -> u.getID().equals(id)).findFirst().orElse(null);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }


//    public Users getUserByUsername(@RequestParam String username) {
//        return usersRepository.getUserByUsername(username);
//    }
//
//    public Users getUserByEmail(@RequestParam String email) {
//        return usersRepository.getUserByEmail(email);
//    }

    // PASSWORD TEMPLATE

    @PostMapping("/register")
    public void registerUser(@RequestBody Users user) {
        // Hash the password using BCrypt before storing it
        // password
        this.passwordEncoder = new BCryptPasswordEncoder();
        String plainText = user.getPassword();
        String encryptedPassword = this.passwordEncoder.encode(plainText);
        System.out.println(encryptedPassword);
        user.setPassword(encryptedPassword);
        // Save the user in the data store

        usersRepository.save(user);

    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody Users loginUser) {
//        boolean loginSuccess = usersService.verifyUserLogin(loginUser);
//        if (loginSuccess) {
//            return ResponseEntity.ok("Login successful.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
//        }
//    }

   public Users userB = new Users(1L, "John", "Doe", "john@example.com", "John123", "john321", "1990-08-15", null, null);

    public static void test() {
//        System.out.println(); // sout

       registerUser(userB);
    }

    public static void main(String[] args) {
          test();
    }

 
}
