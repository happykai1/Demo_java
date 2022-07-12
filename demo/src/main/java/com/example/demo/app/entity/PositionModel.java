package com.example.demo.app.entity;

import com.example.demo.app.repository.PositionRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = PositionRepository.TABLE)
public class PositionModel {
    @Id
    @Column(name="POSITION_ID",nullable = false)
    private String positionId;

    @Column(name="POSITION_NAME")
    private String positionName;
}
