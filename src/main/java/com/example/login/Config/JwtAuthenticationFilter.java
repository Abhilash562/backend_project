package com.example.login.Config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.login.DTO.ApiResponse;
import com.example.login.Entity.Users;
import com.example.login.Service.UsersService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsersService userService;
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
    	String path = request.getRequestURI();

        // Skip public endpoints
        if (path.startsWith("/login") || path.startsWith("/register") || path.startsWith("/sendOTP") || path.startsWith("/verifyOTP") || path.startsWith("/product")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No token provided
            sendUnauthorizedResponse(response, "Authorization token is missing");
            return;
        }

        String token = authHeader.substring(7);

        try {
            String mobileNumber = jwtUtil.extractMobileNumber(token);

            Users user = userService.getUserByMobile(mobileNumber);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            // Token invalid or expired
            sendUnauthorizedResponse(response, "Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }
    
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Object> apiResponse = new ApiResponse<>(false, message, null);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.getWriter().flush();
    }

}
