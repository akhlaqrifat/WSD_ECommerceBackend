package com.wsd.eCommerceBackend.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMessageException extends RuntimeException {

    public CustomMessageException(String message) {
        super(CustomException.generateMessage(message));
    }

}
