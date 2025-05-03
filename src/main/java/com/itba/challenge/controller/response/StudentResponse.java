package com.itba.challenge.controller.response;

import lombok.Builder;

@Builder
public record StudentResponse(
    String name,
    String lastname,
    String email,
    String address
) {
}
