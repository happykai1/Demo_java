package com.example.demo.app.service;

import com.example.demo.app.dto.request.user.UserRequest;

import com.example.demo.app.entity.UserModel;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.repository.PositionRepository;
import com.example.demo.app.repository.RoleRepository;
import com.example.demo.app.repository.UserRepository;
import com.example.demo.core.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.core.ResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PositionRepository positionRepository;

    public ResponseBody<Object> listUser() {
        var users = userRepository.findAll().stream()
                .map(user -> UserModel.builder()
                        .userId(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .userName(user.getUserName())
                        .password(user.getPassword())
                        .status(user.getStatus())
                        .role(user.getRole())
                        .position(user.getPosition())
                        .build())
                .collect(Collectors.toList());

        var json = new ObjectMapper().createObjectNode();
        json.putPOJO("users", users);

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, json);
        return response;
    }

    @Transactional
    public ResponseBody<Object> saveUser(UserRequest request){

        if (request.getUserId() == null || request.getUserId().isEmpty()){
            var role = roleRepository.findByRoleId(request.getRoleId());
            if(role == null){
                throw new DemoException(HttpStatus.OK,INVALID_ROLE_ID);
            }
            var position = positionRepository.findByPositionId(request.getPositionId());
            if(position == null){
                throw new DemoException(HttpStatus.OK,INVALID_POSITION_ID);
            }
            var user = UserModel.builder()
                    .userId(UUID.randomUUID().toString().replace("-", ""))
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .userName(request.getUserName())
                    .password(request.getPassword())
                    .role(role)
                    .position(position)
                    .status(request.getStatus())
                    .build();
            userRepository.save(user);

        }else{
            var user = userRepository.findByUserId(request.getUserId());
            var role = roleRepository.findByRoleId(request.getRoleId());
            if(role == null){
                throw new DemoException(HttpStatus.OK,INVALID_ROLE_ID);
            }
            var position = positionRepository.findByPositionId(request.getPositionId());
            if(position == null){
                throw new DemoException(HttpStatus.OK,INVALID_POSITION_ID);
            }
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setRole(role);
            user.setPosition(position);
            user.setStatus(request.getStatus());
            userRepository.save(user);
        }

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }

    @Transactional
    public ResponseBody<Object> deleteUser(String userId){

        userRepository.deleteById(userId);

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }
}
