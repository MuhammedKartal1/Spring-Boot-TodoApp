package com.project.todoapp.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtTokenProvider {

  //  @Value("${todoapp.app.secret}")
    private String APP_SECRET = "TodoApp";

   // @Value("${todoapp.expires.in}")
    private Long EXPIRES_IN = 1200000L;

    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(APP_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Long getUserIdFromJwt(String token){
        String id =  extractClaim(token, Claims::getSubject);
        return Long.parseLong(id);
    }

    public String generateJwtToken(Authentication auth) {

        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);

        return Jwts.builder()
                    .setSubject(Long.toString(userDetails.getId()))
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                    .compact();
    }

    public String generateJwtTokenByUserId(Long userId) {

        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);

        return Jwts.builder()
                    .setSubject(Long.toString(userId))
                    .setIssuedAt(new Date()).setExpiration(expireDate)
                    .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                    .compact();
    }



    boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token);

            return !isTokenExpired(token);

        } catch ( MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
