package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.model.AppUser;
import com.miu.minionlinemarkert.repository.AppUserRepository;
import com.miu.minionlinemarkert.service.AppUserService;
import com.miu.minionlinemarkert.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final EmailUtil emailUtil;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, EmailUtil emailUtil) {
        this.appUserRepository = appUserRepository;
        this.emailUtil = emailUtil;
    }


    @Override
    public void save(AppUser appUser) {
        if (appUser.getId() != null) {
            appUser.setCreateDate(getById(appUser.getId()).getCreateDate());
            appUser.setModifiedDate(System.currentTimeMillis());
        } else {
            appUser.setCreateDate(System.currentTimeMillis());
        }
        appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = getByUserName(s);
        return new User(user.getEmail(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
    }

    @Override
    public AppUser getByUserName(String s) {
        return appUserRepository.findByEmail(s);
    }

    @Override
    public void updateUserStatus(String id, boolean status) {
        AppUser appUser = getById(id);
        appUser.setApproved(status);
        appUser.setModifiedDate(System.currentTimeMillis());
        appUserRepository.save(appUser);
        if (status)
            emailUtil.sendEmail(appUser.getEmail(), AppConstant.REGISTER_EMAIL_SUBJECT, AppConstant.WELCOME_MESSAGE);

    }

    @Override
    public AppUser getById(String id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }
}
