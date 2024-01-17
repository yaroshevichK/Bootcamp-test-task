package it.bootcamp.handler;

import it.bootcamp.exceptions.NoContentException;
import it.bootcamp.exceptions.NotCorrectPageException;
import it.bootcamp.exceptions.NotUniqueException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @Builder
    @Getter
    public static class ErrorMessage {
        private Date timeStamp;
        private int status;
        private String message;
    }

    //ошибки валидатора
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(
            MethodArgumentNotValidException e) {

        Map<String, String> mapErrors = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors()
                .forEach(error -> {
                    String field = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
                    String message = error.getDefaultMessage();
                    if (mapErrors.containsKey(field)) {
                        mapErrors.put(field, mapErrors.get(field) + "; " + message);
                    } else {
                        mapErrors.put(field, message);
                    }
                });

        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timeStamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(mapErrors.toString())
                .build();


        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {NotUniqueException.class})
    public ResponseEntity<ErrorMessage> handleNotUniqueExceptions(NotUniqueException e) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timeStamp(new Date())
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<ErrorMessage> handleNotUniqueExceptions(NoContentException e) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timeStamp(new Date())
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(value = {NotCorrectPageException.class})
    public ResponseEntity<ErrorMessage> handleNotUniqueExceptions(NotCorrectPageException e) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timeStamp(new Date())
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    //unknown error
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleOtherExceptions(Exception e) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timeStamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
