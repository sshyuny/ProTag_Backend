package com.sshyu.protag.application.service;

import org.springframework.stereotype.Service;

import com.sshyu.protag.domain.auth.model.SessionToken;
import com.sshyu.protag.domain.auth.port.in.LoginUseCase;
import com.sshyu.protag.domain.auth.port.in.SessionManager;
import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.out.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService implements LoginUseCase {
    
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @Override
    public SessionToken login(Member member) {
        Member findedMember = memberRepository.getValidMemberForLogin(member.getLoginId());
        findedMember.authenticate(member.getPassword());
        return sessionManager.createSessionFor(member);
    }
}
