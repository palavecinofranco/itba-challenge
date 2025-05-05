package com.itba.challenge.controller.request;

public record StudentUpdateRequest(
        String name,
        String lastname,
        String dni,
        String email,
        String address) {
}
