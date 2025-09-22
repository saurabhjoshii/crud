package com.crud.operation.repository;

import com.crud.operation.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    List<UserDetail> findByFirstName(String firstName);

    Optional<UserDetail> findByEmailId(String emailId);

    Optional<UserDetail> findByPhoneNo(String phoneNumber);
}
