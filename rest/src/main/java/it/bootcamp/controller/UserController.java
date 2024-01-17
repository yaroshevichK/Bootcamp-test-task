package it.bootcamp.controller;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static it.bootcamp.util.ConstantsRest.DEFAULT_PAGE;
import static it.bootcamp.util.ConstantsRest.PARAM_PAGE_NUMBER;
import static it.bootcamp.util.ConstantsRest.USERS_BY_PAGE_POINT;
import static it.bootcamp.util.ConstantsRest.USERS_POINT;

@RequiredArgsConstructor
@RestController
@RequestMapping(USERS_POINT)
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


    @GetMapping(value = USERS_BY_PAGE_POINT)
    public ResponseEntity<List<UserList>> getUsersByPage(
            @RequestParam(value = PARAM_PAGE_NUMBER,
                    defaultValue = DEFAULT_PAGE,
                    required = false)
            int pageNumber
    ) {
        Page<UserList> pageUsers = userService.getUsersByPage(pageNumber);

        return new ResponseEntity<>(pageUsers.getContent(), HttpStatus.OK);
    }

}
