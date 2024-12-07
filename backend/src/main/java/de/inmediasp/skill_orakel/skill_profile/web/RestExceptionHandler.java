package de.inmediasp.skill_orakel.skill_profile.web;

import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.ResponseBuilder;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.ErrorResponse;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.DatabaseAccessException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NoPermissionToGetSkillProfileException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NotAuthenticatedException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.SkillProfileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice("de.inmediasp.skill_orakel.skill_profile.web")
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(SkillProfileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(SkillProfileNotFoundException exception,
            WebRequest request) {

        ErrorResponse errorResponse = ResponseBuilder.describeError()
                .withStatusCode(HttpStatus.NOT_FOUND)
                .andErrorMessage(exception.getMessage())
                .andDescription(request.getDescription(false))
                .thatOccuredAtTimestamp(LocalDateTime.now())
                .done();

        LOGGER.error("Exception occurred: {}, Request Details: {}",
                exception.getMessage(),
                request.getDescription(false),
                exception
        );

        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(NoPermissionToGetSkillProfileException.class)
    public ResponseEntity<ErrorResponse> handleNoPermissionToGetSkillProfileException(
            NoPermissionToGetSkillProfileException exception, WebRequest request) {

        ErrorResponse errorResponse = ResponseBuilder.describeError()
                .withStatusCode(HttpStatus.FORBIDDEN)
                .andErrorMessage(exception.getMessage())
                .andDescription(request.getDescription(false))
                .thatOccuredAtTimestamp(LocalDateTime.now())
                .done();

        LOGGER.error("Exception occurred: {}, Request Details: {}",
                exception.getMessage(),
                request.getDescription(false),
                exception
        );

        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseAccessException(DatabaseAccessException exception,
            WebRequest request) {
        
        ErrorResponse errorResponse = ResponseBuilder.describeError()
                .withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .andErrorMessage(exception.getMessage())
                .andDescription(request.getDescription(false))
                .thatOccuredAtTimestamp(LocalDateTime.now())
                .done();

        LOGGER.error("Exception occurred: {}, Request Details: {}",
                exception.getMessage(),
                request.getDescription(false),
                exception
        );

        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthenticatedException(NotAuthenticatedException exception,
                                                                         WebRequest request) {
        ErrorResponse errorResponse = ResponseBuilder.describeError()
                .withStatusCode(HttpStatus.UNAUTHORIZED)
                .andErrorMessage(exception.getMessage())
                .andDescription(request.getDescription(false))
                .thatOccuredAtTimestamp(LocalDateTime.now())
                .done();

        LOGGER.error("Exception occurred: {}, Request Details: {}",
                exception.getMessage(),
                request.getDescription(false),
                exception
        );

        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }
}
