package com.robotdreams.assignment9.controller;

import com.robotdreams.assignment9.dto.UserRequestDto;
import com.robotdreams.assignment9.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserRequestDto userRequestDto) {

        return userService.save(userRequestDto)
                ? new ResponseEntity<>("Successfully Created!", HttpStatusCode.valueOf(201))
                : new ResponseEntity<>("An Unexpected Error Occured!", HttpStatusCode.valueOf(500));

    }

}
