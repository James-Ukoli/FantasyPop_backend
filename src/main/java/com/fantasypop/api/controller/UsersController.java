
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
import java.util.List;



@RestController
@RequestMapping(path = "/users")
public class UsersController {

private PasswordEncoder passwordEncoder;

private UsersRepository usersRepository;

@Autowired
UsersService usersService;

  
@GetMapping /// list
 public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @GetMapping ("/{id}")/// read by Id
    public Users getUserById(@PathVariable Long id){
        Users user = usersService.getUserById(id);
        if (user == null) {
            String notFound = "User Not found";
            System.out.println(notFound);
        }
        return user;
        //Response entity?
    }

@GetMapping("/{username}") // read by Username
    public Users getUserByUsername(@RequestParam String username) {
       return usersService.getUserByUsername(username);
    }

    @GetMapping("/{email}") //read by Email
  public Users getUserByEmail(@RequestParam String email) {
        return usersService.getUserByEmail(email);
    }

    /// Login (get user by email && encrypted Password??"


    @PostMapping("/register") // create
    public void registerUser(@RequestBody Users user) {

        // password - encrypt the password  before storing it
        this.passwordEncoder = new BCryptPasswordEncoder();
        String plainText = user.getPassword();
        String encryptedPassword = this.passwordEncoder.encode(plainText);
        System.out.println(encryptedPassword);
        user.setPassword(encryptedPassword);
        /// End of Bcrypt checkpoint

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

    @PutMapping("/{id}") // update
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser){
        Users user = usersService.getUserById(id);
        if (user != null) {
            // Assuming you have appropriate setters in the Users class for updating individual fields
            if (updatedUser.getUsername() != null) {
                user.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getFirstname() != null) {
                user.setFirstname(updatedUser.getFirstname());
            }
            if (updatedUser.getLastname() != null) {
                user.setLastname(updatedUser.getLastname());
            }
            if (updatedUser.getDob() != null) {
                user.setDob(updatedUser.getDob());
            }
            if (updatedUser.getProfilePic() != null) {
                user.setProfilePic(updatedUser.getProfilePic());
            }
            if (updatedUser.getLastname() != null) {
                user.setLastname(updatedUser.getLastname());
            }
            if (updatedUser.getSocialMediaLinks() != null) {
                user.setSocialMediaLinks(updatedUser.getSocialMediaLinks());
            }
            // ... and so on for other fields you want to update

            usersService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


@DeleteMapping // delete
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
