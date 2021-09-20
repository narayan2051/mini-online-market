package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.model.Role;
import com.miu.minionlinemarkert.repository.AppUserRepository;
import com.miu.minionlinemarkert.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user= getByUserName(s);
        List<GrantedAuthority> authorities= new ArrayList<>();
        for(Role role: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getEmail(), user.getPassword(),authorities);
    }

    private AppUser getByUserName(String s) {
        return appUserRepository.findByEmail(s);
    }
}
