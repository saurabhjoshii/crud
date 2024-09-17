package com.crud.operation.controller;

import com.crud.operation.entity.UserDetail;
import com.crud.operation.model.UserDetailModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IRestController {

    @PostMapping("/create")
    ResponseEntity<Object> createUser(@Valid @RequestBody UserDetail userDetail);

    @PatchMapping("/update/{id}")
    ResponseEntity<Object> updateUser(@Valid @RequestBody UserDetailModel userDetailModel, @PathVariable int id);

    @GetMapping("/getUser")
    ResponseEntity<Object> getUser(@RequestParam(required = false) String firstName, @RequestParam(required = false) String emailId, @RequestParam(required = false) String phoneNo);
}
