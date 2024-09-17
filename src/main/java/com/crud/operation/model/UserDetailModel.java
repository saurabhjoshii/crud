package com.crud.operation.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDetailModel {

    @Size(min = 3, max = 50, message = "First Name must be between 2 and 50 characters")
    private String firstName;

    @Size(min = 0, max = 50, message = "Last Name must be between 0 and 50 characters")
    private String lastName;

    @Email(message = "Enter Valid Email Id")
    @Size(max = 30)
    private String emailId;

    @Size(min = 10, max = 10)
    private String phoneNo;

    private int age;
}
