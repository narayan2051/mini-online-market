package com.miu.minionlinemarkert.service;

import com.miu.minionlinemarkert.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends GenericService<AppUser>, UserDetailsService {
    AppUser getById(Long id);
    AppUser getByUserName(String userName);
}
