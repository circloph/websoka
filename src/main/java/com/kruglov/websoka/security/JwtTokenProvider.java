package com.kruglov.websoka.security;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private Long validityInMilliseconds;
    private SecretKey key;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String createToken(Authentication authentication) {

        JwtUser user = (JwtUser) authentication.getPrincipal();

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getAuthorities().stream().findFirst());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest servletRequest) {
        String bearerToken = servletRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
     }
    
}
