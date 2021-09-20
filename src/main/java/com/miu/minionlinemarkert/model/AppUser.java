package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(cascade= CascadeType.ALL)
    private Set<Role> roles;

    @OneToMany(mappedBy="user")
    private List<Order> orders;

    @OneToOne
    private Cart cart;

    @OneToOne
    private Address address;

    @OneToOne
    private Payment payment;
}
