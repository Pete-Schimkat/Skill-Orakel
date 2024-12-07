package de.inmediasp.skill_orakel.skill_profile.web.exception_handling.error_response;

import java.time.LocalDateTime;

public interface DescriptionMediator {
    TimestampMediator thatOccuredAtTimestamp(LocalDateTime timeStamp);
}
