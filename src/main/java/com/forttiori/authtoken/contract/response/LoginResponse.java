package com.forttiori.authtoken.contract.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String username,
        String role,
        String token
) {
}
