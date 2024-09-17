package com.crud.operation.repository;

import com.crud.operation.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    List<UserDetail> findByFirstName(String firstName);

    List<UserDetail> findByEmailId(String emailId);

    List<UserDetail> findByPhoneNo(String phoneNumber);
}
