package com.one.unknown.auth.controller;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.auth.model.dto.LoginDto;
import com.one.unknown.auth.model.service.AuthService;
import com.one.unknown.auth.model.vo.LoginResponse;
import com.one.unknown.token.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	private final TokenService tokenService;
	
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginDto lr){
		
		LoginResponse login = authService.login(lr);
		return ResponseEntity.ok().body(ApiResponse.success("로그인 성공입니다.", login));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Map<String,String>>> getRefreshToken(@RequestBody Map<String,String>refreshToken){
		Map<String, String> tokens = tokenService.tokenRotation(refreshToken.get("refreshToken"));
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(tokens));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<Map<String,String>>> logout(@RequestParam("id") String userId){
		tokenService.logout(userId);
		return ResponseEntity.status(200).body(ApiResponse.success("로그아웃 성공", null));
	}
	
	
	
	
	
}
