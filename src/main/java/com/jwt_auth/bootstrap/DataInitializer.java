package com.jwt_auth.bootstrap;

import com.jwt_auth.constants.RoleNames;
import com.jwt_auth.entity.Role;
import com.jwt_auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String @NonNull ... args) {

        if (!roleRepository.existsByName(RoleNames.USER)) {
            roleRepository.save(
                    Role.builder()
                            .name(RoleNames.USER)
                            .build()
            );
        }

        if (!roleRepository.existsByName(RoleNames.ADMIN)) {
            roleRepository.save(
                    Role.builder()
                            .name(RoleNames.ADMIN)
                            .build()
            );
        }
    }
}
