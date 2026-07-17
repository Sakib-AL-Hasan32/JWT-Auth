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
import jakarta.transaction.Transactional;
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
    @Transactional
    public void run(String @NonNull ... args) {




        // ============================================ //
        // ============= ADD PERMISSIONS ============== //
        // ============================================ //

        Permission profileRead = createPermissionIfNotExists(PermissionNames.PROFILE_READ);
        Permission addProduct = createPermissionIfNotExists(PermissionNames.ADD_PRODUCT);
        Permission getAllProduct = createPermissionIfNotExists(PermissionNames.GET_ALL_PRODUCT);
        Permission getProductById = createPermissionIfNotExists(PermissionNames.GET_PRODUCT_BY_ID);
        Permission getProductByName = createPermissionIfNotExists(PermissionNames.GET_PRODUCT_BY_NAME);
        Permission updateProduct = createPermissionIfNotExists(PermissionNames.UPDATE_PRODUCT);
        Permission deleteProduct = createPermissionIfNotExists(PermissionNames.DELETE_PRODUCT);
        Permission getAllUsers = createPermissionIfNotExists(PermissionNames.GET_ALL_USERS);
        Permission getUserById = createPermissionIfNotExists(PermissionNames.GET_USER_BY_ID);
        Permission addRole = createPermissionIfNotExists(PermissionNames.ADD_ROLE);

        // ============================================ //




        // ============================================ //
        // ================= ADD ROLES ================ //
        // ============================================ //

        Role userRole = createRoleIfNotExists(RoleNames.USER);
        Role adminRole = createRoleIfNotExists(RoleNames.ADMIN);
        Role managerRole = createRoleIfNotExists(RoleNames.MANAGER);

        // ============================================ //




        // ============================================ //
        // ============= USER PERMISSIONS ============= //
        // ============================================ //

        assignPermission(userRole, profileRead);
        assignPermission(userRole, getAllProduct);
        assignPermission(userRole, getProductById);
        assignPermission(userRole, getProductByName);

        // ============================================ //




        // ============================================ //
        // ============= ADMIN PERMISSIONS ============ //
        // ============================================ //

        assignPermission(adminRole, profileRead);
        assignPermission(adminRole, addProduct);
        assignPermission(adminRole, getAllProduct);
        assignPermission(adminRole, getProductById);
        assignPermission(adminRole, getProductByName);
        assignPermission(adminRole, updateProduct);
        assignPermission(adminRole, deleteProduct);
        assignPermission(adminRole, getAllUsers);
        assignPermission(adminRole, getUserById);
        assignPermission(adminRole, addRole);

        // ============================================ //




        // ============================================ //
        // =========== MANAGER PERMISSIONS ============ //
        // ============================================ //

        assignPermission(managerRole, profileRead);
        assignPermission(managerRole, addProduct);
        assignPermission(managerRole, getAllProduct);
        assignPermission(managerRole, getProductById);
        assignPermission(managerRole, getProductByName);
        assignPermission(managerRole, updateProduct);
        assignPermission(managerRole, getAllUsers);
        assignPermission(managerRole, getUserById);

        // ============================================ //




        // ============================================ //
        // =============== ADD NEW ADMIN ============== //
        // ============================================ //

        createAdminIfNotExists(adminRole);

        // ============================================ //




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
