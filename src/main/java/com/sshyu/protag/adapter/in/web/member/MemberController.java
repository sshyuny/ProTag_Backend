package com.sshyu.protag.adapter.in.web.member;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.protag.domain.auth.port.in.LoginUseCase;
import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.in.MemberUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    
    private final MemberUseCase memberUseCase;
    private final LoginUseCase loginUseCase;
    
    @PostMapping("/api/withoutlogin/member")
    public String createMember(@RequestBody MemberReqDto memberReqDto) {

        Member member = Member.builder()
            .memberName(memberReqDto.getMemberName())
            .loginId(memberReqDto.getLoginId())
            .password(memberReqDto.getPassword())
            .build();

        memberUseCase.register(member);

        return "success";
    }

    @DeleteMapping
    public String delete(@RequestBody MemberReqDto memberReqDto) {

        Member member = Member.builder()
            .memberName(memberReqDto.getMemberName())
            .loginId(memberReqDto.getLoginId())
            .password(memberReqDto.getPassword())
            .build();

        memberUseCase.withdraw(member);
        loginUseCase.logout();

        return "success";
    }

}
