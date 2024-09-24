package com.example.user.controller;

import com.example.user.exceptions.UserNotFoundException;
import com.example.user.model.dto.UserDto;
import com.example.user.model.entity.User;
import com.example.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser (userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        log.info("User with id {} has been deleted", id);
        return ResponseEntity.ok("User with id " + id + " has been deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
