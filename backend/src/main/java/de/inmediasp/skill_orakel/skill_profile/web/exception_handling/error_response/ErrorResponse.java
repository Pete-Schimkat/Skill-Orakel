package de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatusCode statusCode;
    private String responseMessage;
    private String description;
    private LocalDateTime timeStamp;
}


