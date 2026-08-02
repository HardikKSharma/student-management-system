package com.project.studentManagement.controller;

import com.project.studentManagement.dto.AdminLoginRequestDTO;
import com.project.studentManagement.dto.LoginResponseDTO;
import com.project.studentManagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AdminLoginRequestDTO requestDTO) {
      return ResponseEntity.ok(adminService.login(requestDTO));
    }
}
