package com.itba.challenge.controller.response;

import lombok.Builder;

@Builder
public record StudentResponse(
    String fullName,
    String dni,
    String email,
    String address
) {
}
