package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.business.UserBusinessDto;
import com.example.Fashion_News_App.dto.web.UserResponseDto;
import com.example.Fashion_News_App.entity.UserEntity;
import com.example.Fashion_News_App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserServiceIF {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserBusinessDto businessDto) {
        if (userRepository.existsByEmail(businessDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(businessDto.getEmail());
        userEntity.setPasswordHash(passwordEncoder.encode(businessDto.getPasswordHash()));
        userEntity.setName(userEntity.getName());
        userEntity.setIsActive(true);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserResponseDto(
                savedUserEntity.getId(),
                savedUserEntity.getEmail()
        );
    }
}
