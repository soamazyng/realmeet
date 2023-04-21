package br.com.sw2you.realmeet.validator;

import lombok.Data;

@Data
public class ValidationError {
    private final String field;
    private final String errorCode;
}
