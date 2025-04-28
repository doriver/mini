package com.ex.mini.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    FAIL_ORDER(HttpStatus.INTERNAL_SERVER_ERROR, "주문에 실패했습니다.")
    ,FAIL_SAVE_ITEM(HttpStatus.INTERNAL_SERVER_ERROR, "아이템 등록에 실패했습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String message;
}
