package br.com.lab.impacta.account.api.handler;

import br.com.lab.impacta.account.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String GENERAL_ERROR_MESSAGE = "Houve um erro interno na aplicação.";

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountNotFoundException e) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                e.getMessage(),
                e.getDescription()
        );
        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> withoutBalanceException(AccountWithoutBalanceException e) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                e.getMessage(),
                e.getDescription()
        );
        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> generalError(RuntimeException e) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                e.getMessage(),
                GENERAL_ERROR_MESSAGE
        );
        return new ResponseEntity<>(errorMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
