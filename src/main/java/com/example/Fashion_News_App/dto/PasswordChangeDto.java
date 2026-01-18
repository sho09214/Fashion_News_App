package com.example.Fashion_News_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordChangeDto {
    private String currentPassword;
    private String newPassword;
}
