package account.database.management.system.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class RepeatedPasswordErrorResponse extends RuntimeException {
    private final String entityName;

    public RepeatedPasswordErrorResponse(String entityName) {
        this.entityName = entityName;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException() {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Not Acceptable",
                "There exists " + entityName + " with such password!!!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
