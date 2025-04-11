package com.sshyu.protag.domain.auth.port.in;

import com.sshyu.protag.domain.auth.model.SessionToken;
import com.sshyu.protag.domain.member.model.Member;

public interface LoginUseCase {
    
    SessionToken login(Member member);

    void logout();

}
