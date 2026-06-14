package com.one.unknown.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.board.model.dto.BoardDto;
import com.one.unknown.board.model.service.BoardService;
import com.one.unknown.like.model.dto.LikeDto;
import com.one.unknown.like.model.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
@Slf4j
public class BoardController {
	private final BoardService boardService;
	private final LikeService likeService;
	
	

	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveBoard(@ModelAttribute BoardDto board, @RequestParam(name="files", required=false) List<MultipartFile> files) {
		log.info("filse{}, board{}", files, board);
		System.out.println(board.getPassword());
		boardService.saveBoard(board, files);
		System.out.println("33333333");
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	}
	
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<BoardDto>>> findAll(@RequestParam(name="page", defaultValue="1")int page) {
		List<BoardDto>boards = boardService.findAll(page);
		return ResponseEntity.ok().body(ApiResponse.success(boards));
	}
	
	
	
	@PatchMapping
	public ResponseEntity<ApiResponse<Void>> updateBoard(@ModelAttribute BoardDto board, @RequestParam(name="files", required=false) List<MultipartFile> files) {
		boardService.updateBoard(board, files);
		return ResponseEntity.ok().body(ApiResponse.success("게시글 수정 성공했습니다."));
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteBoard(@RequestBody BoardDto board) {
		boardService.deleteBoard(board);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("게시글 삭제 성공했습니다."));
	}
	
	@GetMapping("/{boardNo}")
	public ResponseEntity<ApiResponse<BoardDto>> findByBno(@PathVariable(name="boardNo")Long boardNo) {
		BoardDto board = boardService.findByBno(boardNo);
		return ResponseEntity.ok().body(ApiResponse.success(board));
	}
}
