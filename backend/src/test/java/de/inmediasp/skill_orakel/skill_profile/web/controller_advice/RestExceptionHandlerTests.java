package de.inmediasp.skill_orakel.skill_profile.web.controller_advice;

import de.inmediasp.skill_orakel.skill_profile.web.RestExceptionHandler;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.ErrorResponse;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.DatabaseAccessException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NoPermissionToGetSkillProfileException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NotAuthenticatedException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.SkillProfileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RestExceptionHandlerTests {
    
    private final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    @Test
    void WHEN_handleUserNotFoundExceptionIsIntercepted_THEN_RETURN_errorObjectWithStatusCode404() {

        WebRequest mockRequest = mock(WebRequest.class);
        SkillProfileNotFoundException mockException = mock(SkillProfileNotFoundException.class);
        ErrorResponse errorResponse = mock(ErrorResponse.class);

        ResponseEntity<ErrorResponse> expectedResult = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        ResponseEntity<ErrorResponse> response = restExceptionHandler.handleUserNotFoundException(mockException, mockRequest);
        
        assertEquals(expectedResult.getStatusCode(), response.getStatusCode());
    }

    @Test
    void WHEN_handleNoPermissionToGetSkillProfileException_THEN_ResponseEntityShouldHaveStatusCode403() {

        WebRequest mockRequest = mock(WebRequest.class);
        NoPermissionToGetSkillProfileException mockException = mock(NoPermissionToGetSkillProfileException.class);
        ErrorResponse errorResponse = mock(ErrorResponse.class);

        ResponseEntity<ErrorResponse> expectedResult = new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        ResponseEntity<ErrorResponse> response = 
            restExceptionHandler.handleNoPermissionToGetSkillProfileException(mockException, mockRequest);
        
        assertEquals(expectedResult.getStatusCode(), response.getStatusCode());
    }

    
    @Test
    void WHEN_handleDatabaseAccessException_THEN_ResponseEntityShouldHaveStatusCode500() {

        WebRequest mockRequest = mock(WebRequest.class);
        DatabaseAccessException mockException = mock(DatabaseAccessException.class);
        ErrorResponse errorResponse = mock(ErrorResponse.class);

        ResponseEntity<ErrorResponse> expectedResult = 
            new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ErrorResponse> response = restExceptionHandler.handleDatabaseAccessException(mockException, mockRequest);
        
        assertEquals(expectedResult.getStatusCode(), response.getStatusCode());
    }

    @Test
    void WHEN_NotAuthenticatedException_THEN_ResponseEntityShouldHaveStatusCode401() {

        WebRequest mockRequest = mock(WebRequest.class);
        NotAuthenticatedException mockException = mock(NotAuthenticatedException.class);
        ErrorResponse errorResponse = mock(ErrorResponse.class);

        ResponseEntity<ErrorResponse> expectedResult =
                new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        ResponseEntity<ErrorResponse> response = restExceptionHandler.handleNotAuthenticatedException(mockException, mockRequest);

        assertEquals(expectedResult.getStatusCode(), response.getStatusCode());
    }
}
