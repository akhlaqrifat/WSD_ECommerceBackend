package com.wsd.eCommerceBackend.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMessage404Exceptions extends RuntimeException {

    public CustomMessage404Exceptions(String message) {
        super(CustomException.generateMessage(message));
    }

}
