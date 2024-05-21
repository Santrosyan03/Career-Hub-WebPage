package account.database.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RepeatedIdErrorResponse extends RuntimeException {
    @ExceptionHandler(RepeatedIdErrorResponse.class)
    public ResponseEntity<ErrorResponse> handleException() {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Not Acceptable",
                "Account with such ID already exists!!!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
