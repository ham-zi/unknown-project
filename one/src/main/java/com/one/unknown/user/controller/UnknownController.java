package com.one.unknown.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.user.model.service.UnknownService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unknown")
public class UnknownController {
	private final UnknownService userService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<String>> getUserName() {
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.created("아이디가 발급되었습니다.", userService.getUserName()));
	}
}
