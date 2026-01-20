package com.mysite.jwtdemo.security.service;

import com.mysite.jwtdemo.model.User;
import com.mysite.jwtdemo.security.dto.AuthenticatedUserDto;
import com.mysite.jwtdemo.security.dto.RegistrationRequest;
import com.mysite.jwtdemo.security.dto.RegistrationResponse;

/**
 * 유저 인증(로그인, 가입) 할때 서비스
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
