package de.inmediasp.skill_orakel.skill_profile.web.controller_advice;

import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.ResponseBuilder;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseBuilderTest {
    
    private ResponseBuilder responseBuilder;
    private final HttpStatus statusCode = HttpStatus.NOT_FOUND;
    private final String description = "While loading data";
    private final String errorMessage = "An Error has occured";
    private final LocalDateTime timeStamp = LocalDateTime.of(23, 3, 4, 3, 56, 17, 1843);

    @BeforeEach
    void init(){
        this.responseBuilder = new ResponseBuilder();
    }

    @Test
    public void WHEN_withStatusCodeIsCalledWithStatusCode404_ThenResponseBuilderHasStatusCode404(){ 
        responseBuilder.withStatusCode(statusCode);

        assertEquals(statusCode, responseBuilder.getStatusCode());
    }

    @Test
    public void WHEN_andDescriptionsIsCalledWithDescription_WhileLoadingData_ThenResponseBuilderHasThisDescriptiton(){
        responseBuilder.andDescription(description);

        assertEquals(description, responseBuilder.getDescription());
    }

    
    @Test
    public void WHEN_andErrorMessageIsCalledWithMessage_AnErrorHasOccurred_ThenResponseBuilderHasThisMessage(){
        responseBuilder.andErrorMessage(errorMessage);

        assertEquals(errorMessage, responseBuilder.getErrorMessage());
    }

        
    @Test
    public void WHEN_thatOccuredAtTimestampIsCalledWithTimestamp_AnErrorHasOccurred_ThenResponseBuilderHasThisTimeStamp(){
        responseBuilder.thatOccuredAtTimestamp(timeStamp);

        assertEquals(timeStamp, responseBuilder.getTimeStamp());
    }

    @Test
    public void WHEN_doneIsCalled_ThenResponseBuilderHasAllFieldsInitialized(){
        responseBuilder.withStatusCode(statusCode)
            .andErrorMessage(errorMessage)
            .andDescription(description)
            .thatOccuredAtTimestamp(timeStamp);
        
        ErrorResponse expectedResponse = new ErrorResponse(statusCode, errorMessage, description, timeStamp);

        assertEquals(expectedResponse, responseBuilder.done());
    }
}
