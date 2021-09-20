package com.miu.minionlinemarkert.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @NotNull
    @Email
    private String username;
    @NotNull
    @Size(min = 1)
    private String password;
    private boolean rememberMe;
}
