package com.miu.minionlinemarkert.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
public class SignUp {
    @NotEmpty
    @Size(min = 2)
    private String firstName;
    @NotEmpty
    @Size(min=2)
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min=2)
    private String password;
}
