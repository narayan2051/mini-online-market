package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser,String> {
    AppUser findByEmail(String email);
}
