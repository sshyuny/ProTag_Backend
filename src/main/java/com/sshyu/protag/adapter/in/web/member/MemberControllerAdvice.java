package com.sshyu.protag.adapter.in.web.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sshyu.protag.domain.member.exception.DuplicateLoginIdException;
import com.sshyu.protag.domain.member.exception.InvalidLoginException;
import com.sshyu.protag.domain.member.exception.InvalidPasswordException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handleInvalidLogin(InvalidLoginException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body("잘못된 아이디 또는 비밀번호입니다.");
    }

}
