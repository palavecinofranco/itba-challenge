package com.itba.challenge.dto;

import lombok.Builder;

@Builder
public record StudentDTO(
    String name,
    String lastname,
    String dni,
    String email,
    String address) { }
