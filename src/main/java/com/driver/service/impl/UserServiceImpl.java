package com.driver.service.impl;

import com.driver.converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity=UserConverter.dtoToEntity(user);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        return UserConverter.entityToDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        return UserConverter.entityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        long id=Long.parseLong(userId);
        UserEntity userEntity=userRepository.findById(id).get();
        UserEntity newEntity=UserEntity.builder()
                .id(userEntity.getId()).userId(user.getUserId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail()).build();
        userRepository.save(newEntity);
        return UserConverter.entityToDto(newEntity);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        long id=Long.parseLong(userId);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList= (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList=new ArrayList<>();
        for(UserEntity userEntity:userEntityList) userDtoList.add(UserConverter.entityToDto(userEntity));
        return userDtoList;
    }
}