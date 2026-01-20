package com.mysite.jwtdemo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 커스텀 인증에러 클래스
 */
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
