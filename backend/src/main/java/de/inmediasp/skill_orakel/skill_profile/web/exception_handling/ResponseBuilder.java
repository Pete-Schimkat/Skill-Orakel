package de.inmediasp.skill_orakel.skill_profile.web.exception_handling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.CreateErrorMediator;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.DescriptionMediator;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.ErrorMessageMediator;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.ErrorResponse;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.StatusCodeMediator;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response.TimestampMediator;
import lombok.Data;

@Data
public class ResponseBuilder implements StatusCodeMediator, DescriptionMediator, 
            ErrorMessageMediator, TimestampMediator, CreateErrorMediator {
    
    private HttpStatusCode statusCode;
    private String description;
    private String errorMessage;
    private LocalDateTime timeStamp;

    public static CreateErrorMediator describeError(){
        return new ResponseBuilder();
    }

    @Override
    public StatusCodeMediator withStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public ErrorMessageMediator andErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
    
    @Override
    public DescriptionMediator andDescription(String description) {
        this.description = description;
        return this;
    }
    
    public TimestampMediator thatOccuredAtTimestamp(LocalDateTime timeStamp){
        this.timeStamp = timeStamp;
        return this;
    }
    
    @Override
    public ErrorResponse done() {
        return new ErrorResponse(statusCode, errorMessage, description, timeStamp);
    }
}