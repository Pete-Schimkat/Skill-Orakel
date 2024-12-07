package de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response;

import org.springframework.http.HttpStatusCode;

public interface CreateErrorMediator {
    StatusCodeMediator withStatusCode(HttpStatusCode statusCode);
}
