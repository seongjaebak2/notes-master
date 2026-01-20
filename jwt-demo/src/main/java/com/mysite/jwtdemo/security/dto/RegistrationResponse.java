package com.mysite.jwtdemo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 가입후 메세지 전달
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

	private String message;

}
