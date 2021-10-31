package io.dilankam.springsecurityjwt.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author DilankaM
 * @created 31/10/2021 - 11:15
 */
@Data
@RequiredArgsConstructor
public class AuthResponse {

    private final String jwt;
}
