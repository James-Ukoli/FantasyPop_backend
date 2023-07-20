package com.fantasypop.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();

        User user1 = new User(id:1, firstname: "Chris", lastname: "Santos", email: "jimmyjobjob@yahoo.com", password: "large546#", birthday: "", profilePic: "", socialMediaLinks: "" )
    }
    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user: userList) {
            if(id == user.getID()){
                optional = Optional.of(user)
                return optional;
            }
        }
        return optional
    }
}
