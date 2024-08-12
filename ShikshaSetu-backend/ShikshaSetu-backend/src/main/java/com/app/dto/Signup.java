package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entities.Avatar;
import com.app.entities.Subscription;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Signup {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name required")
    private String fullName;

    @Email(message = "Invalid Email!!!")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = Access.READ_ONLY)
    private UserRole role = UserRole.USER; // Default value

    @JsonProperty(access = Access.READ_ONLY)
    private Subscription subscription = Subscription.INACTIVE; // Default value

    private Avatar avatar;

    public Signup(String fullName, String email, String password, Avatar avatar) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
        this.subscription = Subscription.INACTIVE;
        this.avatar = avatar;
    }
}
