package com.secure.notes.security.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank(message = "유저네임을 입력해주세요")
    @Size(min = 2, max = 20, message = "유저네임은 2~20자까지 가능합니다.")
    private String username;

    @NotBlank(message = "이메일을 입력해주세요")
    @Size(max = 50)
    @Email
    private String email;

    @Getter
    @Setter
    private Set<String> role;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 4, max = 10, message = "패스워드는 4~10자까지 가능합니다.")
    private String password;
}
