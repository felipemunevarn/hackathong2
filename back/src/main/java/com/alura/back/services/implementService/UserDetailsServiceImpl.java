package com.alura.back.services.implementService;

import com.alura.back.Dtos.requestDto.AuthCreateUserRequest;
import com.alura.back.Dtos.requestDto.AuthLoginRequest;
import com.alura.back.Dtos.responseDto.AuthResponse;
import com.alura.back.entities.Role;
import com.alura.back.entities.User;
import com.alura.back.repositories.RoleRepository;
import com.alura.back.repositories.UserRepository;
import com.alura.back.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exist"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        user.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String userEmail = authLoginRequest.userEmail();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(userEmail, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(userEmail, "User logged succesfully", accessToken, true);
        return authResponse;
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String userEmail = authCreateUserRequest.userEmail();
        String password = authCreateUserRequest.password();
        List<String> roleList = authCreateUserRequest.roleRequest().roleListName();

        Set<Role> roleSet = roleRepository.findRolesByRoleEnumIn(roleList).stream().collect(Collectors.toSet());

        if (roleSet.isEmpty()) {
            throw new IllegalArgumentException("Role specified does not exist");
        }

        User user = User.builder()
                .email(userEmail)
                .password(passwordEncoder.encode(password))
                .roles(roleSet)
                .build();

        User userCreated = userRepository.save(user);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userCreated.getRoles()
                .stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(userCreated.getEmail(), "User created succesfully", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String userEmail, String password) {
        UserDetails userDetails = this.loadUserByUsername(userEmail);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid userEmail or Password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userEmail, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
