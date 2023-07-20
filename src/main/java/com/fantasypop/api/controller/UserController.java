
package com.fantasypop.api.controller;
import com.fantasypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;
    @Autowired // Constructor injection
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping // create
    @GetMapping // list
    @GetMapping /// read
    public User getUser(@RequestParam Integer id){
       Optional<User> = userService.getUser(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
    @PutMapping // update
    @DeleteMapping // delete

}
