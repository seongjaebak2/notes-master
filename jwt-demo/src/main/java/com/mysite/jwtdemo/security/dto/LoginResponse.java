package com.mysite.jwtdemo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 성공후 프론트에 토큰을 전달
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

	private String token;

}
