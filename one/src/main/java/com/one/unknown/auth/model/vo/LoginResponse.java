package com.one.unknown.auth.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginResponse {
	private String username;
	private String role;
	private String accessToken;
	private String refreshToken;
}
