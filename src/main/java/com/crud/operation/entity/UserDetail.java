package com.crud.operation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name = "UserDetail", uniqueConstraints = {@UniqueConstraint(columnNames = "emailId"), @UniqueConstraint(columnNames = "phoneNo")})
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First Name is Mandatory")
    private String firstName;

    private String lastName;

    @NotNull(message = "Email Id is Mandatory")
    private String emailId;

    @NotNull(message = "Phone Number is Mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    @Column(updatable = false)
    private String phoneNo;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 58, message = "Age cannot be more than 58")
    private int age;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "UserDetail{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", emailId='" + emailId + '\'' + ", phoneNo=" + phoneNo + ", age=" + age + '}';
    }
}
