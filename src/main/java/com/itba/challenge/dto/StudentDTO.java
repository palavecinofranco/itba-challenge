package com.itba.challenge.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record StudentDTO(
    Long id,
    String name,
    String lastname,
    String email,
    String address,
    LocalDate dateOfBirth
) { }
