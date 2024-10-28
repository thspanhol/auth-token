package com.forttiori.authtoken.infrastructure.repository.user;

import com.forttiori.authtoken.contract.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class UserEntity {
    @Id
    private String userId;
    private String username;
    private String password;
    private String role;

    public UserResponse fromEntity() {
        return UserResponse.builder()
                .userId(userId)
                .username(username)
                .role(role)
                .build();
    }
}
