package com.one.unknown.token.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.one.unknown.auth.model.vo.CustomUserDetails;
import com.one.unknown.exception.NotMatchesTokenException;
import com.one.unknown.token.model.dao.TokenMapper;
import com.one.unknown.token.model.vo.RefreshToken;
import com.one.unknown.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtUtil jwtUtil;
	private final TokenMapper tokenMapper;
	
	@Transactional
	public Map<String,String> getTokens(CustomUserDetails user) {
		Map<String,String> tokens= createTokens(user);
		saveToken(tokens.get("refreshToken"), user.getUsername());
		return tokens;
	}
	
	@Transactional
	private Map<String,String> createTokens(CustomUserDetails user) {
		String refreshToken = jwtUtil.createRefreshToken(user);
		
		return Map.of("accessToken",jwtUtil.createAccessToken(user), "refreshToken", refreshToken );
	}
	
	@Transactional
	private void saveToken(String token, String username) {
		RefreshToken refreshToken = RefreshToken.builder()
		            .token(token)
		            .refUser(username)
		            .expiration(System.currentTimeMillis() + (1000*60*60*24*1))
		            .build();
		tokenMapper.saveToken(refreshToken);
	}
	
	@Transactional
	public Map<String, String> tokenRotation(String refreshToken) {
		RefreshToken token = tokenMapper.findByToken(refreshToken);
		if(token == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new NotMatchesTokenException("유효하지 않는 토큰입니다.");
		}
		Claims claims = jwtUtil.jwtParse(refreshToken);
		String userId = claims.getSubject();
		CustomUserDetails user = CustomUserDetails.builder().username(userId).build();
		deleteToken(refreshToken);
		return getTokens(user); 
	}
	
	private void deleteToken(String refreshToken) {
		tokenMapper.deleteToken(refreshToken);
	}
}
