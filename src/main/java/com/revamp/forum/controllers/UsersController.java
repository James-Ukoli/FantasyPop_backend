package com.revamp.forum.controllers;

import java.util.ArrayList;
import com.revamp.forum.data.FieldHelper;
import com.revamp.forum.data.User;
import com.revamp.forum.data.UserRole;
import com.revamp.forum.dto.UserFetchDTO;
import com.revamp.forum.repositories.UsersRepository;
import com.revamp.forum.services.AuthBuddy;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.rest.webmvc.ControllerUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

    @CrossOrigin
    @AllArgsConstructor
    @RestController
    @RequestMapping(value = "/api/user", produces = "application/json")
    public class UsersController {
        private UsersRepository usersRepository;
        private AuthBuddy authBuddy;
        @GetMapping("/{id}")
        public Optional<User> fetchUserById(@PathVariable long id) {
            Optional<User> optionalUser = usersRepository.findById(id);
            if(optionalUser.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
            }
            return optionalUser;
        }
        @GetMapping("")
        public List<UserFetchDTO> fetchUsers(){
            List<User> users = usersRepository.findAll();
            List<UserFetchDTO> userDTOs = new ArrayList<>();
            for (User user : users ){
                UserFetchDTO userDTO = new UserFetchDTO();
                userDTO.setId(user.getId());
                userDTO.setUserName(user.getUserName());
                userDTO.setEmail(user.getEmail());
                userDTO.setRole(user.getRole());
                userDTO.setProfilePic(user.getProfilePic());
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        @GetMapping("/authinfo")
        private UserFetchDTO getUserAuthInfo(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
            User loggedInUser = authBuddy.getUserFromAuthHeader(authHeader);
            UserFetchDTO userDTO = new UserFetchDTO();
            userDTO.setId(loggedInUser.getId());
            userDTO.setEmail(loggedInUser.getEmail());
            userDTO.setRole(loggedInUser.getRole());
            userDTO.setUserName(loggedInUser.getUserName());
            userDTO.setProfilePic(loggedInUser.getProfilePic());
            return userDTO;
        }
        @GetMapping("/me")
        private User fetchMe(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
            User loggedInUser = authBuddy.getUserFromAuthHeader(authHeader);
            return loggedInUser;
        }
        @PostMapping("/create")
        public void createUser(@RequestBody User newUser) {
            newUser.setRole(UserRole.USER);
            newUser.setEmail(newUser.getEmail());
            newUser.setProfilePic(newUser.getProfilePic());
            newUser.setUserName(newUser.getUserName());
            newUser.setCreatedAt(LocalDate.now());
            usersRepository.save(newUser);
        }
        @PostMapping("")
        public void create(@RequestParam(name = "email") String email, @RequestParam(name = "username") String username){
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setEmail(email);
            usersRepository.save(newUser);
        }
        @DeleteMapping("/{id}")
        public void deleteUserById(@PathVariable long id) {
            Optional<User> optionalUser = usersRepository.findById(id);
            if(optionalUser.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
            }
            usersRepository.deleteById(id);
        }
        @PutMapping("/{id}")
        public void updateUser(@RequestBody User updatedUser, @PathVariable long id) {
            Optional<User> optionalUser = usersRepository.findById(id);
            if(optionalUser.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
            }
            User originalUser = optionalUser.get();
            BeanUtils.copyProperties(updatedUser, originalUser, FieldHelper.getNullPropertyNames(updatedUser));
            originalUser.setId(id);
            usersRepository.save(originalUser);
        }
        @RequestMapping(method = RequestMethod.POST, value = "/profile-image/upload")
        public ResponseEntity<?> uploadFile(
                @RequestParam("file") MultipartFile uploadfile, HttpServletRequest request) {
            if (uploadfile.isEmpty()) {
                return new ResponseEntity("please select a file!", HttpStatus.OK);
            }
            String path = "";
            try {
//                Long currentMemberId = ControllerUtils.getUserId(request);
//                path = saveUploadedFile(uploadfile,currentMemberId);
                System.out.println("HElo");
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }



            return new ResponseEntity(path, new HttpHeaders(), HttpStatus.OK);
        }
    }
