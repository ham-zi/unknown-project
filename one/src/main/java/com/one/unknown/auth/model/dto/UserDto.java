package com.one.unknown.auth.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String role;
	private LocalDateTime createDate;
	private String status;

}
