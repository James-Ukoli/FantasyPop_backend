
package com.fantasypop.api.controller;
import com.fantasypop.api.model.Users;
import com.fantasypop.api.repo.UsersRepository;
import com.fantasypop.api.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @GetMapping ("/{id}")/// read
    public Users getUserById(@PathVariable Long id){
        Users user = usersService.getUserById(id);
        if (user == null) {
            String notFound = "User Not found";
            System.out.println(notFound);
        }
        return user;
        //Response entity?
    }


    public Users getUserByUsername(@RequestParam String username) {
       return usersService.getUserByUsername(username);
    }
//
    public Users getUserByEmail(@RequestParam String email) {
        return usersService.getUserByEmail(email);
    }

    /// Login (get user by email && encrypted Password??"


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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, String password) {
        boolean loginSuccess = usersService.verifyUserLogin(username, password);
        if (loginSuccess) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }


@DeleteMapping // DELETE
public ResponseEntity<String> deleteResource(@PathVariable Long id) {
    // Your delete logic here
    // Return an appropriate response
    return ResponseEntity.ok("Resource with ID " + id + " deleted successfully");
}
    // USER TESTING

//   public Users userB = new Users(9L, "Bohn", "Boe", "bohn@example.com", "Bohn123", "Bohn321", "1990-08-15", null, null);
//
//    public static void test() {
////        System.out.println(); // sout
//        // public static void main // psvm
//
//
//       System.out.println(registerUser(userB));
//    }
//
//    public static void main(String[] args) {
//          test();
//    }
//
//
}
