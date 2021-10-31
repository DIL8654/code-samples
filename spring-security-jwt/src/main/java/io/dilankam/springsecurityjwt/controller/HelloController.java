package io.dilankam.springsecurityjwt.controller;

import io.dilankam.springsecurityjwt.model.AuthRequest;
import io.dilankam.springsecurityjwt.model.AuthResponse;
import io.dilankam.springsecurityjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DilankaM
 * @created 31/10/2021 - 10:16
 */
@RequiredArgsConstructor
@RestController
public class HelloController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  @RequestMapping("/hello")
  public String hello() {
    return "hello friend!!";
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest)
      throws Exception {

      try{
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
      }
      catch (BadCredentialsException ex)
      {
          throw new Exception("Incorrect username or password",ex);
      }
      UserDetails  userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
      return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
  }

}
