package com.ex.mini.xx;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ValidationDTO {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @Email(message = "이메일형식을 올바르게 입력해주세요.")
    private String email;

    @Min(value = 19, message = "19세 이상만 가입 가능합니다.")
    private int age;
}
/*
    현재 @ExceptionHandler(MethodArgumentNotValidException.class)에서 처리된대로 하면 아래같이 출력됨
    "[age](은)는 19세 이상만 가입 가능합니다. 입력된 값: [10]\n[email](은)는 이메일형식을 올바르게 입력해주세요. 입력된 값: [asdf]\n"
 */