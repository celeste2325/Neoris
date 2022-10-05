package com.neolab.apistudents.controller;

import com.neolab.apistudents.entity.User;
import com.neolab.apistudents.repository.UserRepository;
import com.neolab.apistudents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiuser/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String getUsers() {
        return "Te devuelvo los usuarios";
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User userAdded = userService.addUser(user);
        if (userAdded != null) {
           // return new ResponseEntity<User>(HttpStatus.CREATED);
           return ResponseEntity.created(URI.create(String.format("/users/%d", userAdded.getId())))
                    .body(userAdded);
        }
        return  ResponseEntity.badRequest().body(user);
    }
    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }


}
