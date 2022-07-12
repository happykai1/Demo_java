package com.example.demo.app.controller;

import com.example.demo.app.dto.request.position.PositionRequest;
import com.example.demo.app.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class PositionControllerTest {

    @InjectMocks
    PositionController positionController;

    @Mock
    PositionService positionService;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(positionController, "validator", Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    void listPositionTestSuccess(){
        var response = positionController.listPosition();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void createPositionTestSuccess(){
        var request = PositionRequest.builder()
                .positionId("")
                .positionName("mock")
                .build();
        var response = positionController.createPosition(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updatePositionTestSuccess(){
        var request = PositionRequest.builder()
                .positionId("mock")
                .positionName("mock")
                .build();
        var response = positionController.updatePosition(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
