package com.example.demo.app.repository;

import com.example.demo.app.entity.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionModel,String> {
    String TABLE = "POSITION";

    PositionModel findByPositionId(String positionId);
}
