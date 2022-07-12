package com.example.demo.app.service;

import com.example.demo.app.dto.request.position.PositionRequest;
import com.example.demo.app.entity.PositionModel;
import com.example.demo.app.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.core.ResponseStatus.SUCCESS;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PositionServiceTest {
    @InjectMocks
    PositionService positionService;

    @Mock
    PositionRepository positionRepository;

    @Test
    void createPositionTestSuccess(){

        var request = PositionRequest.builder()
                .positionId("")
                .positionName("mock")
                .build();

        var response = positionService.savePosition(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void updatePositionTestSuccess(){

        var request = PositionRequest.builder()
                .positionId("mock")
                .positionName("mock")
                .build();

        var position = PositionModel.builder()
                .positionId("mock")
                .positionName("mock")
                .build();

        when(positionRepository.findByPositionId(anyString())).thenReturn(position);
        var response = positionService.savePosition(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }
}
