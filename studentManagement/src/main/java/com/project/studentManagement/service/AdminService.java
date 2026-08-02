package com.project.studentManagement.service;

import com.project.studentManagement.dto.AdminLoginRequestDTO;
import com.project.studentManagement.dto.LoginResponseDTO;

public interface AdminService {

    LoginResponseDTO login(AdminLoginRequestDTO request);
}
