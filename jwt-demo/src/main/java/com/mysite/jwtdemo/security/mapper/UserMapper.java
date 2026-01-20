package com.mysite.jwtdemo.security.mapper;

import com.mysite.jwtdemo.model.User;
import com.mysite.jwtdemo.security.dto.AuthenticatedUserDto;
import com.mysite.jwtdemo.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * dto => 엔티티 또는 엔티티 => dto 변환
 * 유저와 관련 dto 객체의 전달값을 변환
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
