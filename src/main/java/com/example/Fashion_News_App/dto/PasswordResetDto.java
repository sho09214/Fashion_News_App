package com.example.Fashion_News_App.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordResetDto {

    private String email;
    private String newPassword;
}
