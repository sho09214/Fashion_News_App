package com.example.Fashion_News_App.security;

import com.example.Fashion_News_App.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        // ★ ログイン・登録APIは JWTチェックしない
        if (path.startsWith("/api/user/login")
                || path.startsWith("/api/user/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ① Authorization ヘッダー取得
        String authHeader = request.getHeader("Authorization");

        // ② Bearer トークンでなければスルー
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // ③ JWT部分だけ取り出す
        String token = authHeader.substring(7);

        try {
            // ④ JWTから userId を取得
            Long userId = jwtUtil.getUserId(token);

            // ⑤ Spring Security に「ログイン中」と伝える
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            Collections.emptyList()
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            // JWT不正・期限切れなど → 403レスポンス
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
