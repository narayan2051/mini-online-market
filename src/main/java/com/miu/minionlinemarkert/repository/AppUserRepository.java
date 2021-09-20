package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByEmail(String email);
}
