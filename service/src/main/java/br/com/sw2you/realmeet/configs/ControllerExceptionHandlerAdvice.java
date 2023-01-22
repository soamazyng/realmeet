package br.com.sw2you.realmeet.configs;

import br.com.sw2you.realmeet.api.model.ResponseError;
import br.com.sw2you.realmeet.exceptions.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exception) {
        return new ResponseEntity<>(
            new ResponseError()
                .status(httpStatus.getReasonPhrase())
                .code(httpStatus.value())
                .message(exception.getMessage()),
            httpStatus
        );
    }
}
