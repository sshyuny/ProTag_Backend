package com.sshyu.protag.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.in.MemberUseCase;
import com.sshyu.protag.domain.member.port.out.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberUseCase {

    private final MemberRepository memberRepository;
    
    @Override
    public void register(Member member) {
        member.validatePassword();
        memberRepository.validateLoginId(member.getLoginId());
        memberRepository.save(member);
    }

}
