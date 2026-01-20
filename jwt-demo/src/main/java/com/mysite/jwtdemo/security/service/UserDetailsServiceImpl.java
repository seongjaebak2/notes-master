package com.mysite.jwtdemo.security.service;

import com.mysite.jwtdemo.model.UserRole;
import com.mysite.jwtdemo.security.dto.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * SLf4j => 롬북 자동 log 객체 생성됨
 * 권한 앞에 "ROLE_" 을 붙여서 시큐리티 유저 설정
 * loadUserByUsername 메소드로 유저를 찾아서 시큐리티의 유저디테일 객체로 리턴
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) {

		final AuthenticatedUserDto authenticatedUser = userService.findAuthenticatedUserByUsername(username);
		//유저를 DB에서 못찾으면 예외 발생
		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException("유저네임을 찾을 수 없습니다");
		}

		final String authenticatedUsername = authenticatedUser.getUsername();
		final String authenticatedPassword = authenticatedUser.getPassword();
		final UserRole userRole = authenticatedUser.getUserRole();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + userRole.name());

		return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
	}
}
