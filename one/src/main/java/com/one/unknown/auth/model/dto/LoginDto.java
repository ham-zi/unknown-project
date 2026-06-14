package com.one.unknown.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
	@NotBlank(message="아이디는 필수입니다.")
	@Size(min=4, max=20, message="아이디는 4글자에서 20글자까지 가능합니다.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 영문 대소문자 숫자만 가능합니다")
	private String userId;
	@NotBlank(message="비밀번호는 필수입니다.")
	@Size(min=4, max=20, message="비밀번호는 4글자에서 20글자까지 가능합니다.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="비밀번호는 영문 대소문자 숫자만 가능합니다")
	private String userPwd;

}
