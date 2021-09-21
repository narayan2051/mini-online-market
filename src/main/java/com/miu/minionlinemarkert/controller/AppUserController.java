package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PreAuthorize("hasAuthority(" + AppConstant.ADMIN + ")")
    @GetMapping
    public List<AppUser> findAll(){
        return appUserService.findAll();
    }

}
