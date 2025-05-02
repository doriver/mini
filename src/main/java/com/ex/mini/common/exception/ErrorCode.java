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
    , FAIL_TRANSACTION(HttpStatus.INTERNAL_SERVER_ERROR, "상품 결제에 실패했습니다.")
    , FAIL_EMPTY_CART(HttpStatus.INTERNAL_SERVER_ERROR, "장바구니 비우기에 실패했습니다.")
    , ZERO_CART(HttpStatus.BAD_REQUEST, "장바구니가 비였습니다.")
    , NEED_TO_LOGIN(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.")
    , ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당아이템은 존재하지 않습니다.")
    , ITEM_SOLD_OUT(HttpStatus.BAD_REQUEST, "해당아이템은 품절되었습니다")
    , ITEM_OUTOF_STOCK(HttpStatus.BAD_REQUEST, "해당아이템의 제고가 부족합니다.")
    , WALLET_NOT_FOUND(HttpStatus.BAD_REQUEST, "지갑이 존재하지 않습니다.")
    , DONT_BUY_MONEY(HttpStatus.BAD_REQUEST, "보유하신 금액이 모잘라 구매할수 없습니다.")
    , NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST, "보유하신 금액이 부족합니다.")

    ;
    private final HttpStatus httpStatus;
    private final String message;
}
