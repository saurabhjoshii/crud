package com.crud.operation.controller;

import com.crud.operation.entity.UserDetail;
import com.crud.operation.model.UserDetailModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IRestController {

    @PostMapping("/users")
    ResponseEntity<Object> createUser(@Valid @RequestBody UserDetail userDetail);

    @GetMapping("/users/{id}")
    ResponseEntity<Object> getUserById(@PathVariable int id);

    @GetMapping("/users")
    ResponseEntity<Object> getAllUsers();

    @GetMapping("/users/search")
    ResponseEntity<Object> searchUsers(@RequestParam(required = false) String firstName, 
                                     @RequestParam(required = false) String emailId, 
                                     @RequestParam(required = false) String phoneNo);

    @PutMapping("/users/{id}")
    ResponseEntity<Object> updateUser(@Valid @RequestBody UserDetailModel userDetailModel, @PathVariable int id);

    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable int id);
}
