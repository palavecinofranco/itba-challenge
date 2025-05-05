package com.itba.challenge.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record StudentCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank @Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener entre 7 y 8 dígitos numéricos.")
        String dni,
        @Email @NotBlank
        String email,
        String address) { }
