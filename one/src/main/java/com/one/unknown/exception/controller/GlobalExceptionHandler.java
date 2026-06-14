package com.one.unknown.exception.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.one.unknown.api.model.vo.ApiResponse;
import com.one.unknown.exception.DatabaseOperationException;
import com.one.unknown.exception.DuplicateUserIdException;
import com.one.unknown.exception.NotFoundBoardException;
import com.one.unknown.exception.NotMatchesPasswordException;
import com.one.unknown.exception.NotMatchesTokenException;
import com.one.unknown.exception.RequestFailBoardException;
import com.one.unknown.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateUserIdException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateUserId(DuplicateUserIdException e) {
		return ResponseEntity.badRequest().body(ApiResponse.duplicateUserId(e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String,String> map = new HashMap<>();
		e.getFieldErrors().forEach(err -> {
			map.put(err.getField(), err.getDefaultMessage());
		});
				
		return ResponseEntity.badRequest().body(ApiResponse.validUserInfo(map));
	}
	
	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<ApiResponse<Void>> handleDatabaseOperation(DatabaseOperationException e) {
		return ResponseEntity.badRequest().body(ApiResponse.DatabaseOperation(e.getMessage()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleUserNotFound(UserNotFoundException e) {
		return ResponseEntity.badRequest().body(ApiResponse.userNotFound(e.getMessage()));
	}
	
	@ExceptionHandler(NotMatchesTokenException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotMatchesToken(NotMatchesTokenException e) {
		return ResponseEntity.badRequest().body(ApiResponse.userNotFound(e.getMessage()));
	}
	@ExceptionHandler(NotFoundBoardException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFoundBoard(NotFoundBoardException e) {
		return ResponseEntity.badRequest().body(ApiResponse.notFoundBoard(e.getMessage()));
	}
	
	@ExceptionHandler(RequestFailBoardException.class)
	public ResponseEntity<ApiResponse<Void>> handleRequestFailBoard(RequestFailBoardException e) {
		return ResponseEntity.badRequest().body(ApiResponse.requestFailBoard(e.getMessage()));
	}
	@ExceptionHandler(NotMatchesPasswordException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotMatchesPassword(NotMatchesPasswordException e) {
		return ResponseEntity.badRequest().body(ApiResponse.notMatchesPassword(e.getMessage()));
	}
	

	
	

}
