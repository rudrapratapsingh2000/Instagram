package com.Instagram.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;
    @Pattern(regexp = "^.+@(?![Ii][Nn][Ss][Tt][Aa][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    private String userPassword;
    @Pattern(regexp = "^\\d{2}\\d{10}$", message = "Invalid phone number format")
    @Size(min = 12, max = 12, message = "Phone number length should be 12 digits")
    private String phoneNumber;
}
