package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.AuthResponse;
import com.miu.minionlinemarkert.DTO.Login;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.service.AppUserService;
import com.miu.minionlinemarkert.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AppUserService appUserService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.appUserService = appUserService;
    }


    @PostMapping
    public ApiResponse login(@RequestBody @Valid Login login, HttpServletResponse servletResponse) {
        AppUser appUser = appUserService.getByUserName(login.getUsername());
//        if(!appUser.isApproved()){
//           return new ApiResponse(ResponseConstant.FAILURE,ResponseConstant.USER_UNAPPROVED);
//        }
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>()));
        if (authentication != null) {
            String role = authentication.getAuthorities().toString();
            role = role.substring(1, role.indexOf("]"));
            String token = jwtUtil.generateToken(authentication);
                     return new AuthResponse(ResponseConstant.SUCCESS, "Login Success", jwtUtil.generateToken(authentication),role);
        }
        return new AuthResponse(ResponseConstant.FAILURE, "Invalid Username/password", null,null);
    }


}
