package com.mysite.jwtdemo.security.service;

import com.mysite.jwtdemo.model.User;
import com.mysite.jwtdemo.model.UserRole;
import com.mysite.jwtdemo.repository.UserRepository;
import com.mysite.jwtdemo.security.dto.AuthenticatedUserDto;
import com.mysite.jwtdemo.security.dto.RegistrationRequest;
import com.mysite.jwtdemo.security.dto.RegistrationResponse;
import com.mysite.jwtdemo.security.mapper.UserMapper;
import com.mysite.jwtdemo.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * SLf4j => 롬북 자동 log 객체 생성됨
 * 유저 인증(로그인, 가입) 할때 서비스 구현
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = username + "님 회원 가입성공!";

		log.info("{} 가입 성공!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
