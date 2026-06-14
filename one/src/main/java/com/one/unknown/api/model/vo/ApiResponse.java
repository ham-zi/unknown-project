package com.one.unknown.api.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {
	private int code;
	private String message;
	private T data;
	
	// 200 성공 응답
	public static <T> ApiResponse<T> success(T data){
		return new ApiResponse<>(200, "요청에 성공했습니다.", data);
		
	}
	
	public static <T> ApiResponse<T> success(String message, T data){
		return new ApiResponse<>(200, message, data);
		
	}
	
	public static <T> ApiResponse<T> success(String message) {
		return new ApiResponse<>(200, message, null);
	}
	
	// 201 성공 응답
	public static <T> ApiResponse<T> created(T data){
		return new ApiResponse<>(201, "요청에 성공했습니다.", data);
		
	}
	
	public static <T> ApiResponse<T> created(String message, T data){
		return new ApiResponse<>(201, message, data);
		
	}
	
	// 204 성공 응답
	public static <T> ApiResponse<T> noContent(T data){
		return new ApiResponse<>(204, "요청에 성공했습니다.", data);
		
	}
	
	// 400 실패 응답
	public static <T> ApiResponse<T> duplicateUserId(String message) {
		return new ApiResponse<>(400, message, null);
	}
	public static <T> ApiResponse<T> duplicateUserId(T data) {
		return new ApiResponse<>(400, "아이디가 중복되었습니다.", data);
	}
	public static <T> ApiResponse<T> duplicateUserId(String message, T data) {
		return new ApiResponse<>(400, message, data);
	}
	
	public static <T> ApiResponse<T> validUserInfo(T data) {
		return new ApiResponse<>(400, "아이디 검증",data);
	}
	
	public static <T> ApiResponse<T> DatabaseOperation(String message){
		return new ApiResponse<>(400, message, null);
	}
	
	public static <T> ApiResponse<T> userNotFound(String message){
		return new ApiResponse<>(400, message, null);
	}
	
	
	public static <T> ApiResponse<T> notMatchesToken(String message){
		return new ApiResponse<>(400, message, null);
	}
	public static <T> ApiResponse<T> notFoundBoard(String message){
		return new ApiResponse<>(400, message, null);
	}
	
	public static <T> ApiResponse<T> requestFailBoard(String message){
		return new ApiResponse<>(400, message, null);
	}
	
	public static <T> ApiResponse<T> notMatchesPassword(String message) {
		return new ApiResponse<>(400, message, null);
	}
	
	
	

	

}
