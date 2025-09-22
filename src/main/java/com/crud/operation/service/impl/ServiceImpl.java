package com.crud.operation.service.impl;

import com.crud.operation.entity.UserDetail;
import com.crud.operation.exception.UserAlreadyExistsException;
import com.crud.operation.exception.UserNotFoundException;
import com.crud.operation.model.UserDetailModel;
import com.crud.operation.repository.UserDetailRepository;
import com.crud.operation.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceImpl implements IService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetailModel createUser(UserDetail userDetail) {
        // Check if user with same email already exists
        if (userDetailRepository.findByEmailId(userDetail.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userDetail.getEmailId() + " already exists");
        }
        
        // Check if user with same phone number already exists
        if (userDetailRepository.findByPhoneNo(userDetail.getPhoneNo()).isPresent()) {
            throw new UserAlreadyExistsException("User with phone number " + userDetail.getPhoneNo() + " already exists");
        }
        
        UserDetail savedUser = userDetailRepository.save(userDetail);
        return buildUserDetailModel(savedUser);
    }

    @Override
    public UserDetailModel updateUser(UserDetailModel userDetailModel, int id) {
        UserDetail existingUser = userDetailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        
        // Check if email is being changed and if new email already exists
        if (userDetailModel.getEmailId() != null && 
            !userDetailModel.getEmailId().equals(existingUser.getEmailId())) {
            Optional<UserDetail> userWithEmail = userDetailRepository.findByEmailId(userDetailModel.getEmailId());
            if (userWithEmail.isPresent() && userWithEmail.get().getId() != id) {
                throw new UserAlreadyExistsException("User with email " + userDetailModel.getEmailId() + " already exists");
            }
        }
        
        // Update fields only if they are provided
        if (userDetailModel.getFirstName() != null) {
            existingUser.setFirstName(userDetailModel.getFirstName());
        }
        if (userDetailModel.getLastName() != null) {
            existingUser.setLastName(userDetailModel.getLastName());
        }
        if (userDetailModel.getEmailId() != null) {
            existingUser.setEmailId(userDetailModel.getEmailId());
        }
        if (userDetailModel.getAge() != null) {
            existingUser.setAge(userDetailModel.getAge());
        }
        
        UserDetail updatedUser = userDetailRepository.save(existingUser);
        return buildUserDetailModel(updatedUser);
    }

    @Override
    public UserDetailModel getUserById(int id) {
        UserDetail user = userDetailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return buildUserDetailModel(user);
    }

    @Override
    public List<UserDetailModel> getAllUsers() {
        List<UserDetail> users = userDetailRepository.findAll();
        return users.stream()
                .map(this::buildUserDetailModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailModel> getUserData(String firstName, String emailId, String phoneNo) {
        List<UserDetail> users;
        
        if (firstName != null && !firstName.trim().isEmpty()) {
            users = userDetailRepository.findByFirstName(firstName);
        } else if (emailId != null && !emailId.trim().isEmpty()) {
            users = userDetailRepository.findByEmailId(emailId).map(List::of).orElse(List.of());
        } else if (phoneNo != null && !phoneNo.trim().isEmpty()) {
            users = userDetailRepository.findByPhoneNo(phoneNo).map(List::of).orElse(List.of());
        } else {
            users = userDetailRepository.findAll();
        }
        
        return users.stream()
                .map(this::buildUserDetailModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id) {
        if (!userDetailRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userDetailRepository.deleteById(id);
    }

    private UserDetailModel buildUserDetailModel(UserDetail userDetail) {
        return UserDetailModel.builder()
                .id(userDetail.getId())
                .firstName(userDetail.getFirstName())
                .lastName(userDetail.getLastName())
                .emailId(userDetail.getEmailId())
                .phoneNo(userDetail.getPhoneNo())
                .age(userDetail.getAge())
                .build();
    }
}
