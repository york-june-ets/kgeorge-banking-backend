package solutions.york.bankingbackend.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException e) {
        ApiError validationError = new ApiError(400, "Bad Request", e.getMessage());
        return ResponseEntity.badRequest().body(validationError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        ApiError constraintError = new ApiError(409, "Conflict", "Data constraint violation");
        return ResponseEntity.status(409).body(constraintError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError genericError = new ApiError(500, "Internal Server Error", "An unexpected error occurred");
        return ResponseEntity.status(500).body(genericError);
    }
}
