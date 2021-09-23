package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.UpdateUserDTO;
import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserController(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

  // @PreAuthorize("hasAuthority(" + AppConstant.ADMIN + ")")
    @GetMapping
    public List<AppUser> findAll() {
        return appUserService.findAll();
    }

  //  @PreAuthorize("hasAuthority(" + AppConstant.ADMIN + ")")
    @PostMapping("/updateUserStatus")
    public ApiResponse updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        appUserService.updateUserStatus(updateUserDTO.getId(), updateUserDTO.isApproved());
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.USER_UPDATED);
    }

    @PostConstruct
    public void addDefaultUser() {
        AppUser appUser = appUserService.getByUserName("narayan2051@gmail.com");
        if (null == appUser) {
            AppUser user = new AppUser(null, "narayan", "chapagain", "narayan2051@gmail.com", passwordEncoder.encode("1234"), "ADMIN", true, 0, 0);
            appUserService.save(user);
            AppUser user1 = new AppUser(null, "narayan", "chapagain", "narayan2051@yopmail.com", passwordEncoder.encode("1234"), "SELLER", true, 0, 0);
            appUserService.save(user1);
            AppUser user2 = new AppUser(null, "narayan", "chapagain", "narayan@yopmail.com", passwordEncoder.encode("1234"), "USER", true, 0, 0);
            appUserService.save(user2);
        }
    }

}
