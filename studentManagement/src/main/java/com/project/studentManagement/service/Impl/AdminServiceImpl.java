package com.project.studentManagement.service.Impl;

import com.project.studentManagement.dto.AdminLoginRequestDTO;
import com.project.studentManagement.dto.LoginResponseDTO;
import com.project.studentManagement.entity.Admin;
import com.project.studentManagement.repo.AdminRepo;
import com.project.studentManagement.security.JwtService;
import com.project.studentManagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AuthenticationManager authenticationManager;
    private final AdminRepo adminRepo;
    private final JwtService jwtService;
    @Override
    public LoginResponseDTO login(AdminLoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        assert userDetails != null;
        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token);
    }
}
