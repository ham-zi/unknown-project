package com.one.unknown.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.one.unknown.auth.model.dto.LoginDto;
import com.one.unknown.auth.model.vo.CustomUserDetails;
import com.one.unknown.auth.model.vo.LoginResponse;
import com.one.unknown.exception.UserNotFoundException;
import com.one.unknown.token.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
	private final AuthenticationManager authenticationManger;
	private final TokenService tokenService;
	private final PasswordEncoder ps;
	
	
	public LoginResponse login(@Valid LoginDto lr) {
		
//		System.out.println(ps.encode("1234"));
//		log.info("asdf{}",ps.encode("1234"));
		Authentication auth = null;
		try {
			auth = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(lr.getUserId(), lr.getUserPwd()));
//			System.out.println(auth);
//			log.info("넘어오냐ㅐ?");
		} catch(AuthenticationException e) {
			throw new UserNotFoundException("아이디 혹은 비밀번호가 틀렸습니다.");
		}
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		
		Map<String, String> tokens = tokenService.getTokens(user);
		
		return LoginResponse.builder()
				            .username(user.getUsername())
				            .accessToken(tokens.get("accessToken"))
				            .role(user.getAuthorities().toString())
				            .refreshToken(tokens.get("refreshToken"))
				            .build();

	}
	
}
