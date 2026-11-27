package com.vti.payment_api.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                Claims claims = jwtUtil.parseToken(header.substring(7));
                Long userId = ((Number) claims.get("userId")).longValue();

                var authentication = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null,
                        List.of());
                authentication.setDetails(userId);   // Controller lấy lại bằng authentication.getDetails()
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RuntimeException e) {
                // Token sai / hết hạn / thiếu thông tin: không đăng nhập cho request này -> bị chặn bên dưới (401)
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }
}
