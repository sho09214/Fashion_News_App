package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.PasswordChangeDto;
import com.example.Fashion_News_App.dto.web.UserCurrentResponseDto;
import com.example.Fashion_News_App.repository.MytagRepository;
import com.example.Fashion_News_App.util.JwtUtil;
import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;
import com.example.Fashion_News_App.entity.UserEntity;
import com.example.Fashion_News_App.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserServiceIF {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final MytagRepository mytagRepository;

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

    //退会
    @Override
    public void withdraw(Long userId) {
        UserEntity user = userRepository
                .findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //マイタグ削除
        mytagRepository.deleteByUserId(userId);

        //ユーザー論理削除
        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
    }

    @Transactional
    @Override
    public void changePassword(Long userId, PasswordChangeDto passwordChangeDto) {
        UserEntity userEntity = userRepository
                .findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //現在のパスワード確認
        if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), userEntity.getPasswordHash())) {
            throw new RuntimeException("現在のパスワードが正しくありません");
        }

        //新旧パスワードの同一チェック
        if (passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword())) {
            throw new RuntimeException("新しいパスワードは現在のものと異なる必要があります");
        }

        //新パスワードをハッシュ化
        String encodedNewPassword = passwordEncoder.encode(passwordChangeDto.getNewPassword());
        userEntity.setPasswordHash(encodedNewPassword);
    }

    @Override
    public UserCurrentResponseDto getCurrentUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("ユーザーが見つかりませんでした"));

        UserCurrentResponseDto userCurrentResponseDto = new UserCurrentResponseDto();
        userCurrentResponseDto.setUserId(userId);
        userCurrentResponseDto.setName(userEntity.getName());
        userCurrentResponseDto.setEmail(userEntity.getEmail());

        return userCurrentResponseDto;
    }
}
