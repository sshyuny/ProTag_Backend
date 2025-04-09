package com.sshyu.protag.domain.member.exception;

public class InvalidPasswordException extends RuntimeException {
    
    public InvalidPasswordException(String message) {
        super(message);
    }
    
}
