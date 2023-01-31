package com.example.decagon_Life_Project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.decagon_Life_Project.entities.UserEntity} entity
 */
@Data
@Setter @Getter
@AllArgsConstructor
public class UserEntityDto implements Serializable {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;
    private  String confirmPassword;
    private Boolean subscribeToNewsletter;
}