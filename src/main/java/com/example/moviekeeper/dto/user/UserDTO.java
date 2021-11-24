package com.example.moviekeeper.dto.user;

import com.example.moviekeeper.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private long id;
    private String username;
    private Role role;
}
