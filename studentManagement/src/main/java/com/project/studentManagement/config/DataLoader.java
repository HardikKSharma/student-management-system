package com.project.studentManagement.config;

import com.project.studentManagement.entity.Admin;
import com.project.studentManagement.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if(adminRepo.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminRepo.save(admin);
        }
    }
}
