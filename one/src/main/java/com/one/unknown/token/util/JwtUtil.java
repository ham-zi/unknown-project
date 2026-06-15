package com.one.unknown.token.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.one.unknown.auth.model.vo.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		byte[] arr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(arr);
	}
	
	//getToken
	public String createAccessToken(CustomUserDetails user) {
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(540))))
				   .signWith(key)
				   .compact();
	}
	//refreshToken
	public String createRefreshToken(CustomUserDetails user) {
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
				   .signWith(key)
				   .compact();
	}
	//Claims메서드
	public Claims jwtParse(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
}
