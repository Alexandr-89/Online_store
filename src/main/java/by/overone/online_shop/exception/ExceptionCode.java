package by.overone.online_shop.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;


public enum ExceptionCode {

    NOT_EXISTING_USER("1"),
    NOT_EXISTING_PRODUCT("2");


    private final String errorCode;


    ExceptionCode(String errorCode) {
        this.errorCode=errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }


}
