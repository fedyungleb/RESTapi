package lt.viko.eif.hlibfediun.exception;

import lt.viko.eif.hlibfediun.model.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Exception handler class that handles runtime exceptions that are thrown in the service layer.
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApplicationResponse> handleNoSuchElementException(NoSuchElementException e) {
        ApplicationResponse response = new ApplicationResponse(e.getMessage(), 404);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApplicationResponse> handleIllegalStateException(IllegalStateException e) {
        ApplicationResponse response = new ApplicationResponse(e.getMessage(), 204);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
