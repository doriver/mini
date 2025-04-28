package com.ex.mini.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    FAIL_ORDER(HttpStatus.INTERNAL_SERVER_ERROR, "주문에 실패했습니다.")
    , FAIL_SAVE_ITEM(HttpStatus.INTERNAL_SERVER_ERROR, "아이템 등록에 실패했습니다.")
    , FAIL_SAVE_CART(HttpStatus.INTERNAL_SERVER_ERROR, "아이템을 장바구니에 담기 실패했습니다.")
    , FAIL_SAVE_DELIVERY(HttpStatus.INTERNAL_SERVER_ERROR, "배송정보 입력 실패했습니다.")
    , ZERO_CART(HttpStatus.BAD_REQUEST, "장바구니가 비였습니다.")
    , NEED_TO_LOGIN(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.")

    ;
    private final HttpStatus httpStatus;
    private final String message;
}
