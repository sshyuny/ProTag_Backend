package com.sshyu.protag.domain.member.model;

import com.sshyu.protag.domain.member.exception.InvalidLoginException;
import com.sshyu.protag.domain.member.exception.InvalidPasswordException;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
    
    private String memberName;
    private String loginId;
    private String password;
    private int isDeleted;

    public void authenticate(String reqPassword) {
        if (!password.equals(reqPassword)) { throw new InvalidLoginException("잘못된 비밀번호"); }
        if (isDeleted == 1) { throw new InvalidLoginException("삭제된 계정"); }
    }

    public void validatePassword() {
        int passwordLength = password.length();
        if (passwordLength < 8 || passwordLength > 40) { throw new InvalidPasswordException("비밀번호 길이가 부적합합니다."); }

        boolean hasAlphabet = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasAlphabet = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasAlphabet && hasDigit) {
                return;
            }
        }

        throw new InvalidPasswordException("비밀번호에는 알파벳과 숫자가 모두 들어가야합니다.");
    }

}
