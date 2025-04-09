package com.sshyu.protag.domain.member.exception;

public class DuplicateLoginIdException extends RuntimeException {
    
    public DuplicateLoginIdException(String message) {
        super(message);
    }
    
}
