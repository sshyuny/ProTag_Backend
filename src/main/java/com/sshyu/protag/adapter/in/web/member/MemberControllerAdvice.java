package com.sshyu.protag.adapter.in.web.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sshyu.protag.domain.member.exception.DuplicateLoginIdException;
import com.sshyu.protag.domain.member.exception.InvalidPasswordException;

@RestControllerAdvice
public class MemberControllerAdvice {
    
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateLoginIdException.class)
    public ResponseEntity<String> handleDuplicateLoginId(DuplicateLoginIdException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
