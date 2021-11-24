package com.example.moviekeeper.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtRespDTO {
    private long userId;
    private String username;
    private String token;
    private String expiration;
}
