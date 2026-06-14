package com.one.unknown.token.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
	private String token;
	private String refUser;
	private Long expiration;
}
