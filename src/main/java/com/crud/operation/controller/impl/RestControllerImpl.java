package com.crud.operation.controller.impl;

import com.crud.operation.controller.IRestController;
import com.crud.operation.entity.UserDetail;
import com.crud.operation.model.UserDetailModel;
import com.crud.operation.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RestControllerImpl implements IRestController {

    @Autowired
    private IService userService;

    @Override
    public ResponseEntity<Object> createUser(UserDetail userDetail) {
        UserDetailModel createdUser = userService.createUser(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    public ResponseEntity<Object> getUserById(int id) {
        UserDetailModel user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<Object> searchUsers(String firstName, String emailId, String phoneNo) {
        return ResponseEntity.ok(userService.getUserData(firstName, emailId, phoneNo));
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDetailModel userDetailModel, int id) {
        UserDetailModel updatedUser = userService.updateUser(userDetailModel, id);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<Object> deleteUser(int id) {
        userService.deleteUser(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("id", String.valueOf(id));
        return ResponseEntity.ok(response);
    }
}
