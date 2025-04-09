package com.sshyu.protag.domain.member.port.out;

import com.sshyu.protag.domain.member.model.Member;

public interface MemberRepository {
    
    void save(Member member);

    boolean isLoginIdInUse(String loginId);

}
