package com.sshyu.protag.domain.member.port.in;

import com.sshyu.protag.domain.member.model.Member;

public interface MemberUseCase {
    
    /*
     * 회원가입
     */
    void register(Member member);

    /*
     * 회원 탈퇴
     */
    void withdraw(Member member);
    
}
