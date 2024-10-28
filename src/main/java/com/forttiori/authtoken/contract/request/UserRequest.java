package com.forttiori.authtoken.contract.request;

import lombok.Builder;

@Builder
public record UserRequest(
        String username,
        String password,
        String role
) {}
