package com.ex.mini.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class ExpectedException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public ExpectedException(String message) { // 동적으로( 특정 변수 값에 따라 ) 메시지 정할때
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = message;
    }

    public ExpectedException(ErrorCode errorCode) { // 정적으로 정해진 경우
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
