package it.bootcamp.controller;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static it.bootcamp.util.DataRest.USER_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_API)
public class UserController {
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UserList>> getUsers() {
        List<UserList> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
