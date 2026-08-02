package com.project.studentManagement.service;

import com.project.studentManagement.dto.AdminLoginRequestDTO;
import com.project.studentManagement.dto.LoginResponseDTO;
import com.project.studentManagement.repo.AdminRepo;
import com.project.studentManagement.security.JwtService;
import com.project.studentManagement.service.Impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AdminRepo adminRepo;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void loginTest() {

        AdminLoginRequestDTO request = new AdminLoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("admin123");

        UserDetails user = new User(
                "admin",
                "password",
                List.of()
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(jwtService.generateToken(user))
                .thenReturn("jwt-token");

        LoginResponseDTO response = adminService.login(request);

        assertEquals("jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any());
        verify(jwtService).generateToken(user);
    }
}