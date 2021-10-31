package io.dilankam.springsecurityjwt.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author DilankaM
 * @created 31/10/2021 - 11:14
 */
@Data
@RequiredArgsConstructor
public class AuthRequest {
    private String userName;
    private String password;

}
