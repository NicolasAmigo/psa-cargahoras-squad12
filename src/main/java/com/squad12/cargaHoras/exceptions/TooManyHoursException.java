package com.squad12.cargaHoras.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TooManyHoursException extends RuntimeException{

    public TooManyHoursException(String message) {
        super(message);
    }
}
