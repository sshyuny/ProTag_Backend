package com.sshyu.protag.domain.member.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
    
    private String memberName;
    private String loginId;
    private String password;

    public boolean authenticate(String reqPassword) {
        return password.equals(reqPassword);
    }

    public boolean isPasswordValid() {
        int passwordLength = password.length();
        if (passwordLength < 8 || passwordLength > 40) return false;

        boolean hasAlphabet = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasAlphabet = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasAlphabet && hasDigit) {
                return true;
            }
        }
        return false;
    }

}
