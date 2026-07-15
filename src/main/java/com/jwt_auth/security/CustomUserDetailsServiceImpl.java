package com.jwt_auth.security;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.entity.Permission;
import com.jwt_auth.entity.Role;
import com.jwt_auth.entity.User;
import com.jwt_auth.repository.UserRepository;
import com.jwt_auth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(extractAuthorities(user))
                .build();
    }

    private Set<GrantedAuthority> extractAuthorities(User user) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        return authorities;
    }
}
