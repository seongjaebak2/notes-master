package com.mysite.jwtdemo.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 유저 가입할때 사용하는 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest {

	@NotEmpty(message = "이름은 필수")
	private String name;

	@Email(message = "이메일 양식이 맞지않음")
	@NotEmpty(message = "이메일은 필수")
	private String email;

	@NotEmpty(message = "유저네임 필수")
	private String username;

	@NotEmpty(message = "패스워드 필수")
	private String password;

}
