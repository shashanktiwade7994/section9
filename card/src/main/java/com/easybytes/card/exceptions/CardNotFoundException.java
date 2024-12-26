package com.easybytes.card.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("Resource %s not present with name: %s and value %s", resourceName, fieldName, fieldValue));
    }
}
