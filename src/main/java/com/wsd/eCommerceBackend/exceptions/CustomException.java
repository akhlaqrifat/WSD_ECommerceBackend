package com.wsd.eCommerceBackend.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException {

    public CustomException(String errMessage) {
        super(CustomException.generateMessage(errMessage));
    }

    public static String generateMessage(String errMessage) {
        log.error( errMessage);
        return errMessage;
    }
}
