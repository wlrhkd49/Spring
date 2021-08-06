package com.example.eatgo.interfaces;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import com.example.eatgo.application.UserService;
import com.example.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 1. User list
    // 2. User create -> 회원 가입
    // 3. User update
    // 4. User delete -> level: 0 -> 아무 것도 못함
    // (1: customer, 2: restaurant owner, 3: admin)

    @GetMapping("/users")
    public List<User> list() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {

        User user = userService.addUser(resource.getEmail(), resource.getName());

        String url = "/users/" + user.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
