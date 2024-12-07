package de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception;

public class DatabaseAccessException extends RuntimeException {

    public DatabaseAccessException(String message){
        super(message);
    }
    
}
