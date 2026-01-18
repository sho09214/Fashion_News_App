package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.PasswordChangeDto;
import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;
import com.example.Fashion_News_App.service.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceIF userServiceIF;

    //新規会員登録
    @PostMapping("/register")
    public UserRegisterResponseDto register(@RequestBody UserRegisterBusinessDto businessDto) {
        return userServiceIF.register(businessDto);
    }

    //ログイン
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginBusinessDto businessDto) {
        return userServiceIF.login(businessDto);
    }

    //退会
    //論理削除クリーンアップ未実装
    @DeleteMapping("/withdraw")
    public ResponseEntity<Void> withdraw(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userServiceIF.withdraw(userId);
        return ResponseEntity.noContent().build(); //204レスポンス
    }

    //パスワード変更
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(
            @RequestBody PasswordChangeDto passwordChangeDto,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userServiceIF.changePassword(userId, passwordChangeDto);
        return ResponseEntity.noContent().build(); //204レスポンス;
    }
}
