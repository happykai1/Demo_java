package com.example.demo.app.repository;

import com.example.demo.app.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    String TABLE ="USER";

    UserModel findByUserId(String userId);
}
