package com.jwt_auth.bootstrap;

import com.jwt_auth.constants.AdminCredentials;
import com.jwt_auth.constants.PermissionNames;
import com.jwt_auth.constants.RoleNames;
import com.jwt_auth.entity.Permission;
import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import com.jwt_auth.repository.PermissionRepository;
import com.jwt_auth.repository.RoleRepository;
import com.jwt_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String @NonNull ... args) {

        Role userRole = createRoleIfNotExists(RoleNames.USER);
        Role adminRole = createRoleIfNotExists(RoleNames.ADMIN);
        // Add New Roles

        Permission createUser = createPermissionIfNotExists(PermissionNames.CREATE_USER);
        Permission userRead = createPermissionIfNotExists(PermissionNames.USER_READ);
        Permission userUpdate = createPermissionIfNotExists(PermissionNames.USER_UPDATE);
        Permission userDelete = createPermissionIfNotExists(PermissionNames.USER_DELETE);
        Permission profileRead = createPermissionIfNotExists(PermissionNames.PROFILE_READ);
        // Add New Permissions

        assignPermission(userRole, userRead);
        assignPermission(userRole, userUpdate);
        assignPermission(userRole, profileRead);
        // Assign More Permission To User

        assignPermission(adminRole, createUser);
        assignPermission(adminRole, userRead);
        assignPermission(adminRole, userUpdate);
        assignPermission(adminRole, userDelete);
        assignPermission(adminRole, profileRead);
        // Assign More Permission To Admin

        createAdminIfNotExists(adminRole);
        // Create New Admin
    }

    private Role createRoleIfNotExists(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() ->
                        roleRepository.save(
                                Role.builder()
                                        .name(roleName)
                                        .build()
                        )
                );
    }

    private Permission createPermissionIfNotExists(String permissionName) {
        return permissionRepository.findByName(permissionName)
                .orElseGet(() ->
                        permissionRepository.save(
                                Permission.builder()
                                        .name(permissionName)
                                        .build()
                        )
                );
    }

    private void assignPermission(Role role, Permission permission) {
        if (!role.getPermissions().contains(permission)) {
            role.getPermissions().add(permission);
            roleRepository.save(role);
        }
    }

    private void createAdminIfNotExists(Role adminRole) {

        if (userRepository.existsByUsername(AdminCredentials.Admin1.ADMIN_USERNAME)) {
            return;
        }

        User admin = User.builder()
                .firstName(AdminCredentials.Admin1.ADMIN_FIRST_NAME)
                .lastName(AdminCredentials.Admin1.ADMIN_LAST_NAME)
                .username(AdminCredentials.Admin1.ADMIN_USERNAME)
                .email(AdminCredentials.Admin1.ADMIN_EMAIL)
                .password(passwordEncoder.encode(AdminCredentials.Admin1.ADMIN_PASSWORD))
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(admin);
    }
}
