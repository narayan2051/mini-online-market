package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.SignUp;
import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final ModelMapper modelMapper;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public PublicController(ModelMapper modelMapper, AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signup")
    public ApiResponse signUp(@Valid @RequestBody SignUp signUp) {
        AppUser appUser = modelMapper.map(signUp, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(signUp.getPassword()));
        setApproval(appUser);
        appUserService.save(appUser);
        return new ApiResponse(ResponseConstant.SUCCESS,ResponseConstant.SIGN_UP_SUCCESS);
    }

    private void setApproval(AppUser appUser) {
        if(appUser.getRole().equals(AppConstant.SELLER)){
            appUser.setApproved(false);
        }
        appUser.setApproved(true);
    }

}
