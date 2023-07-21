
package com.fantasypop.api.controller;
import com.fantasypop.api.model.User;
import com.fantasypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/vi/user")
public class UserController {

    private final UserService userService;
 // Constructor injection
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

  
    @GetMapping /// list
 public List<User> getUsers(){
        return userService.getUsers();
    }
 
}
