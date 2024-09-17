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

@RestController
@RequestMapping("/api/v1")
public class RestControllerImpl implements IRestController {

    @Autowired
    IService iService;

    @Override
    public ResponseEntity<Object> createUser(UserDetail userDetail) {
        return new ResponseEntity<>(iService.createUser(userDetail), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDetailModel userDetailModel, int id) {
        return new ResponseEntity<>(iService.updateUser(userDetailModel, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUser(String firstName, String emailId, String phoneNo) {
        return new ResponseEntity<>(iService.getUserData(firstName, emailId, phoneNo), HttpStatus.OK);
    }
}
