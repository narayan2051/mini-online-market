package com.miu.minionlinemarkert.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse extends ApiResponse {
    private String token;
    private String role;
    public AuthResponse(String type, String message, String token, String role) {
        super(type, message);
        this.token = token;
        this.role=role;
    }
}
