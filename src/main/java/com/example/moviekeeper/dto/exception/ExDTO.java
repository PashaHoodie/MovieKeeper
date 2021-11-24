package com.example.moviekeeper.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExDTO {

        private LocalDateTime time;

        private int status;

        private String error;

        private String message;

        private String path;
}
