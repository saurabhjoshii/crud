package com.crud.operation.service.impl;

import com.crud.operation.entity.UserDetail;
import com.crud.operation.model.UserDetailModel;
import com.crud.operation.repository.UserDetailRepository;
import com.crud.operation.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ServiceImpl implements IService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetailModel createUser(UserDetail userDetail) {
        return buildUserData(userDetailRepository.save(userDetail));
    }

    @Override
    public UserDetailModel updateUser(UserDetailModel userDetailModel, int id) {
        Optional<UserDetail> userDetail = userDetailRepository.findById(id);
        if (!userDetail.isEmpty()) {
            return buildUserData(saveUserDetail(UserDetail.builder()
                    .firstName(userDetailModel.getFirstName() != null ? userDetailModel.getFirstName() : userDetail.get().getFirstName())
                    .lastName(userDetailModel.getLastName() != null ? userDetailModel.getLastName() : userDetail.get().getLastName())
                    .emailId(userDetailModel.getEmailId() != null ? userDetailModel.getEmailId() : userDetail.get().getEmailId())
                    .phoneNo(userDetail.get().getPhoneNo())
                    .age(userDetailModel.getAge() > 0 ? userDetailModel.getAge() : userDetail.get().getAge())
                    .id(id)
                    .build()));
        } else {
            return userDetailModel;
        }
    }

    private UserDetail saveUserDetail(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public List<UserDetailModel> getUserData(String firstName, String emailId, String phoneNo) {
        if (firstName != null && !firstName.isEmpty())
            return extractUserDetail(userDetailRepository.findByFirstName(firstName));
        else if (emailId != null && !emailId.isEmpty())
            return extractUserDetail(userDetailRepository.findByEmailId(emailId));
        else if (phoneNo != null && !phoneNo.isEmpty())
            return extractUserDetail(userDetailRepository.findByPhoneNo(phoneNo));
        else return extractUserDetail(userDetailRepository.findAll());
    }

    private List<UserDetailModel> extractUserDetail(List<UserDetail> user) {
        List<UserDetailModel> list = new ArrayList<>();
        for (UserDetail detail : user) {
            list.add(buildUserData(detail));
        }
        return list;
    }

    private UserDetailModel buildUserData(UserDetail detail) {
        return UserDetailModel.builder().firstName(detail.getFirstName()).lastName(detail.getLastName()).emailId(detail.getEmailId()).phoneNo(detail.getPhoneNo()).age(detail.getAge()).build();
    }
}
