package com.ex.mini.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExpectedException extends RuntimeException {
    private final ErrorCode errorCode;
}
