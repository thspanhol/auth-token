package com.forttiori.authtoken.contract.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
         String userId,
         String username,
         String role
) {
}
