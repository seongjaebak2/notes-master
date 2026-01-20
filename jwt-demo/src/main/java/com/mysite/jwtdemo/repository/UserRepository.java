package com.mysite.jwtdemo.repository;


import com.mysite.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유저리포
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}
