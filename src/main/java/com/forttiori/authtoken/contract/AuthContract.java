package com.forttiori.authtoken.contract;

import com.forttiori.authtoken.contract.request.LoginRequest;
import com.forttiori.authtoken.contract.request.UserRequest;
import com.forttiori.authtoken.contract.response.LoginResponse;
import com.forttiori.authtoken.contract.response.UserResponse;
import com.forttiori.authtoken.domain.ShoppingCartService;
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
    private final ShoppingCartService shoppingCartService;

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public UserResponse register(@PathVariable String id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @GetMapping("verify")
    @RolesAllowed("ADMIN")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("cart")
    @RolesAllowed("USER")
    public String getCart() {
        return shoppingCartService.getShoppingCart();
    }
}
