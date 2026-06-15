package com.one.unknown.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.board.model.dto.BoardDto;
import com.one.unknown.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
@Slf4j
public class AdminController {
	private final BoardService boardService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<BoardDto>>> findAll(@RequestParam(name="page", defaultValue="1")int page) {
		List<BoardDto>boards = boardService.findAllAsAdmin(page);
		return ResponseEntity.ok().body(ApiResponse.success(boards));
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteBoard(@RequestBody BoardDto board) {
		boardService.deleteBoardAsAdmin(board);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("게시글 삭제 성공했습니다."));
	}
}
