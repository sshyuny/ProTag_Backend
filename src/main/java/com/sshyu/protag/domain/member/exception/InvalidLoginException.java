package com.sshyu.protag.domain.member.exception;

public class InvalidLoginException extends RuntimeException {
    
    public InvalidLoginException(String message) {
        super(message);
    }
}
