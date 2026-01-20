package com.mysite.jwtdemo.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//테스트용 헬로우 컨트롤러
@RestController
public class HelloController {

    @GetMapping("/hello")
    @Operation(tags = "Hello Service", description = "요청할때 헤더에 토큰을 추가 Bearer <JWT토큰>")
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("JWT 테스트 헬로우! 토큰 인증됨!");
    }

}