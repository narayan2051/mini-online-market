package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
    //ToDo: import should be change if we upgrade db to mongo
    @Id
    private Long id;
    private String email;
    private String password;
    @OneToMany(cascade= CascadeType.ALL)
    private Set<Role> roles;
}
