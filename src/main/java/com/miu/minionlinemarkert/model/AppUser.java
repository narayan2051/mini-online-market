package com.miu.minionlinemarkert.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private boolean approved;
    private long createDate;
    private long modifiedDate;
}
