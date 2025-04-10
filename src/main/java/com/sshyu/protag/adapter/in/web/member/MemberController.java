package com.sshyu.protag.adapter.in.web.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.protag.domain.auth.port.in.LoginUseCase;
import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.in.MemberUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
    
    private final MemberUseCase memberUseCase;
    private final LoginUseCase loginUseCase;
    
    @PostMapping("/api/member")
    public String createMember(@RequestBody MemberReqDto memberReqDto) {

        Member member = Member.builder()
            .memberName(memberReqDto.getMemberName())
            .loginId(memberReqDto.getLoginId())
            .password(memberReqDto.getPassword())
            .build();

        memberUseCase.register(member);

        return "success";
    }

    @PostMapping("/api/login")
    public String login(@RequestBody MemberReqDto memberReqDto) {

        Member member = Member.builder()
            .memberName(memberReqDto.getMemberName())
            .loginId(memberReqDto.getLoginId())
            .password(memberReqDto.getPassword())
            .build();

        loginUseCase.login(member);

        return "success";
    }

}
