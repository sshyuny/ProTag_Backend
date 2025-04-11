package com.sshyu.protag.adapter.in.web.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.protag.adapter.in.web.member.MemberReqDto;
import com.sshyu.protag.domain.auth.port.in.LoginUseCase;
import com.sshyu.protag.domain.member.model.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LoginController {
    
    private final LoginUseCase loginUseCase;

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

    @PostMapping("/api/logout")
    public String logout() {
        return "success";
    }

}
