package com.example.demo.app.repository;

import com.example.demo.app.entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,String> {
    String TABLE ="ROLE";

    RoleModel findByRoleId(String roleId);
}
