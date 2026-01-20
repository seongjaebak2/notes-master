package com.mysite.jwtdemo.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 로그인 할때 받는 객체
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

	@NotEmpty(message = "유저네임 필수")
	private String username;

	@NotEmpty(message = "패스워드 필수")
	private String password;

}
