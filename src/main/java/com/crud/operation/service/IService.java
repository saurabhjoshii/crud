package com.crud.operation.service;

import com.crud.operation.entity.UserDetail;
import com.crud.operation.model.UserDetailModel;

import java.util.List;

public interface IService {

    UserDetailModel createUser(UserDetail userDetail);

    UserDetailModel updateUser(UserDetailModel userDetailModel, int id);

    UserDetailModel getUserById(int id);

    List<UserDetailModel> getAllUsers();

    List<UserDetailModel> getUserData(String firstName, String emailId, String phoneNo);

    void deleteUser(int id);
}
