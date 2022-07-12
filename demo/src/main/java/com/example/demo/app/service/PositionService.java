package com.example.demo.app.service;

import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.app.dto.request.position.PositionRequest;
import com.example.demo.app.dto.response.position.PositionResponse;
import com.example.demo.app.entity.PositionModel;
import com.example.demo.app.repository.PositionRepository;
import com.example.demo.core.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.example.demo.core.ResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService {

    final PositionRepository positionRepository;

    public ResponseBody<Object> listPosition() {
        var positions = positionRepository.findAll().stream()
                .map(position -> PositionResponse.builder()
                        .positionId(position.getPositionId())
                        .positionName(position.getPositionName())
                        .build())
                .collect(Collectors.toList());

        var json = new ObjectMapper().createObjectNode();
        json.putPOJO("positions", positions);

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, json);
        return response;
    }

    @Transactional
    public ResponseBody<Object> savePosition(PositionRequest request){

        if (request.getPositionId() == null || request.getPositionId().isEmpty()){
            var position = PositionModel.builder()
                    .positionId(UUID.randomUUID().toString().replace("-", ""))
                    .positionName(request.getPositionName())
                    .build();
            positionRepository.save(position);

        }else{
            var position = positionRepository.findByPositionId(request.getPositionId());
            position.setPositionName(request.getPositionName());
            positionRepository.save(position);
        }

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }
}
