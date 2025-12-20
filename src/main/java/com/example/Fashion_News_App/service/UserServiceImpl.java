package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.Util.JwtUtil;
import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;
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
    private final JwtUtil jwtUtil;

    @Override
    public UserRegisterResponseDto register(UserRegisterBusinessDto businessDto) {
        if (userRepository.existsByEmail(businessDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(businessDto.getEmail());
        userEntity.setPasswordHash(passwordEncoder.encode(businessDto.getPassword()));
        userEntity.setName(businessDto.getName());
        userEntity.setIsActive(true);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserRegisterResponseDto(
                savedUserEntity.getId(),
                savedUserEntity.getEmail()
        );
    }

    @Override
    public UserLoginResponseDto login(UserLoginBusinessDto businessDto) {
        UserEntity user = userRepository.findByEmail(businessDto.getEmail())
                .orElseThrow(() -> new RuntimeException("メールアドレスまたはパスワードが違います"));

        if (!passwordEncoder.matches(businessDto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("メールアドレスまたはパスワードが違います");
        }

        String token = jwtUtil.generateToken(user);

        return new UserLoginResponseDto(
                token,
                user.getId(),
                user.getEmail()
        );
    }
}
