package com.miu.minionlinemarkert.util;

import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.service.AppUserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AppUtil {

    private final AppUserService appUserService;

    public AppUtil(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public AppUser getUserByAuthentication(Authentication authentication){
        return appUserService.getByUserName(authentication.getName());
    }
}
