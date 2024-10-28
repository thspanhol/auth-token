package com.forttiori.authtoken.domain;

import com.forttiori.authtoken.contract.request.LoginRequest;
import com.forttiori.authtoken.contract.request.UserRequest;
import com.forttiori.authtoken.contract.response.LoginResponse;
import com.forttiori.authtoken.contract.response.UserResponse;
import com.forttiori.authtoken.infrastructure.repository.UserEntity;
import com.forttiori.authtoken.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponse login(LoginRequest loginRequest) {
        return userRepository.findByUsername(loginRequest.username()).map(userEntity -> {
            if(passwordEncoder.matches(loginRequest.password(), userEntity.getPassword())) {
                return LoginResponse.builder()
                        .username(userEntity.getUsername())
                        .role(userEntity.getRole())
                        .token(tokenService.generateToken(userEntity))
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password invalid.");
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public UserResponse register(UserRequest userRequest) {
        return userRepository.save(UserEntity.builder()
                        .username(userRequest.username())
                        .password(passwordEncoder.encode(userRequest.password()))
                        .role(userRequest.role())
                .build()).fromEntity();
    }

    public List<UserResponse> getUsers() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findAll().stream().map(UserEntity::fromEntity).toList();
    }
}
