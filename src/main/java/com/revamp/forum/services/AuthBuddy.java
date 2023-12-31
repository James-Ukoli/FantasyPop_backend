package com.revamp.forum.services;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revamp.forum.data.User;
import com.revamp.forum.data.UserRole;
import com.revamp.forum.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class AuthBuddy {

    @Autowired
    private UsersRepository usersRepository;

    public User getUserFromAuthHeader(String authHeader) {
        if(authHeader == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        // grab access token
        String accessToken = getAccessTokenFromHeader(authHeader);
        if(accessToken == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        // assume google auth. get email from google via the access token
        String [] fields = getFieldsFromToken(accessToken);
        if(fields[0] == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        String email = fields[0];
        User user = usersRepository.findByEmail(email);
        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setUserName(email);
            user.setRole(UserRole.USER);
            user.setCreatedAt(LocalDate.now());
//            System.out.println("User not found: " + email);
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            user = usersRepository.save(user);
        }
        user.setProfilePic(fields[1]);
        return user;
    }

    private String getAccessTokenFromHeader(String header) {
        String [] parts = header.split(" ");
        if(parts.length != 2) {
            return null;
        }
        if(!parts[0].equalsIgnoreCase("bearer")) {
            return null;
        }
        return parts[1];
    }

    private String [] getFieldsFromToken(String accessToken) {
        String [] fields = new String[2];
        RestTemplate restTemplate = new RestTemplate();

        String uri = "https://people.googleapis.com/v1/people/me?personFields=names,emailAddresses,photos&access_token=" + accessToken;
        System.out.println(uri);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

//        System.out.println(result.getBody());
        JsonObject jo = JsonParser.parseString(result.getBody().toString()).getAsJsonObject();
        String email = jo.get("emailAddresses").getAsJsonArray().get(0).getAsJsonObject().get("value").toString().replaceAll("\"", "");
        String photo = jo.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("url").toString().replaceAll("\"", "");

        fields[0] = email;
        fields[1] = photo;
        return fields;
    }

}
