package com.forttiori.authtoken.contract;

import com.forttiori.authtoken.contract.request.LoginRequest;
import com.forttiori.authtoken.contract.request.UserRequest;
import com.forttiori.authtoken.contract.response.LoginResponse;
import com.forttiori.authtoken.contract.response.UserResponse;
import com.forttiori.authtoken.domain.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/auth")
public class AuthContract {

    private final UserService userService;

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @GetMapping("verify")
    @RolesAllowed("ADMIN")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
}
