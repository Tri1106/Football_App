package com.example.DACK.config;

import com.example.DACK.model.User;
import com.example.DACK.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User admin = userRepository.findByUsername("admin")
                .orElseGet(User::new);

        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setEmail("admin@footballapp.local");
        admin.setFullName("Administrator");
        admin.setRole(User.Role.ADMIN);

        userRepository.save(admin);
    }
}
