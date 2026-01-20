package com.mysite.jwtdemo.service;

import com.mysite.jwtdemo.exception.RegistrationException;
import com.mysite.jwtdemo.repository.UserRepository;
import com.mysite.jwtdemo.security.dto.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 중복되지 않아야 하는 유저네임과 이메일을 검사해서 중복일 경우에 에러처리
 * Slf4j log 경고 메세지도 작성함
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

	private static final String EMAIL_ALREADY_EXISTS = "이메일 이미 있음";

	private static final String USERNAME_ALREADY_EXISTS = "유저네임 이미 있음";

	private final UserRepository userRepository;

	public void validateUser(RegistrationRequest registrationRequest) {

		final String email = registrationRequest.getEmail();
		final String username = registrationRequest.getUsername();

		checkEmail(email);
		checkUsername(username);
	}

	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			log.warn("Username: {} already being used!", username);

			throw new RegistrationException(USERNAME_ALREADY_EXISTS);
		}

	}

	private void checkEmail(String email) {

		final boolean existsByEmail = userRepository.existsByEmail(email);

		if (existsByEmail) {

			log.warn("Email: {} already being used!", email);

			throw new RegistrationException(EMAIL_ALREADY_EXISTS);
		}
	}

}
