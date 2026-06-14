package com.one.unknown.like.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.like.model.dto.LikeDto;
import com.one.unknown.like.model.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/api/likes")
@RestController
@Slf4j
public class LikeController {
	private final LikeService likeService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> requestLike(@RequestBody LikeDto likeDto){
		int result = likeService.requestLike(likeDto);
		return ResponseEntity.ok().body(ApiResponse.success(result));
	}
}
