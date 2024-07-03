package com.wsd.eCommerceBackend.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloudServiceException extends RuntimeException {

    public CloudServiceException(String errorMessage) {
        super(CustomException.generateMessage(errorMessage));
    }

}
