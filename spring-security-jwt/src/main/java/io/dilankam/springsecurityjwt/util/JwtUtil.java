package io.dilankam.springsecurityjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author DilankaM
 * @created 31/10/2021 - 10:49
 */
@Component
public class JwtUtil {

  /**
   * please do not hard code any secret in your real application, this is illustration purpose only
   */
  private final String SECRET = "jwt-secret";

  public String extractUserName(final String token) {
    return extractClaims(token, Claims::getSubject);
  }

  public <T> T extractClaims(final String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(final String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(final String token) {
    return extractExpiration(token).before(new Date());
  }

  public Date extractExpiration(final String token) {
    return extractClaims(token, Claims::getExpiration);
  }
  public String generateToken(final UserDetails userDetails)
  {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUserName(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
