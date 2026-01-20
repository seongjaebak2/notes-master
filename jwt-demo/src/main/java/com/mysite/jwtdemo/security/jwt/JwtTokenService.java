package com.mysite.jwtdemo.security.jwt;

import com.mysite.jwtdemo.security.mapper.UserMapper;
import com.mysite.jwtdemo.security.service.UserService;
import com.mysite.jwtdemo.model.User;
import com.mysite.jwtdemo.security.dto.AuthenticatedUserDto;
import com.mysite.jwtdemo.security.dto.LoginRequest;
import com.mysite.jwtdemo.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 *  jwt 토큰 서비스
 *  getLoginResponse : 유저 로그인 확인 및 토큰 리턴
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(user);

		log.info("{} 님 로그인 성공!", user.getUsername());

		return new LoginResponse(token);
	}

}
