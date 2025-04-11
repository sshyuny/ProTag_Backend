package com.sshyu.protag.domain.member.port.out;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sshyu.protag.domain.member.exception.DuplicateLoginIdException;
import com.sshyu.protag.domain.member.model.Member;

@ActiveProfiles("test")
@SpringBootTest
public class MemberRepositoryTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입_테스트() {

        memberRepository.validateLoginId("sshyu");

        Member member = Member.builder()
            .memberName("sshyu")
            .loginId("sshyu")
            .password("sshyu1122")
            .build();
        memberRepository.save(member);

        assertThrows(DuplicateLoginIdException.class, () -> memberRepository.validateLoginId("sshyu"));
    }

    @Test
    void 회원탈퇴_테스트() {

        Member newMember = Member.builder()
            .memberName("sshyu")
            .loginId("sshyu")
            .password("sshyu1122")
            .build();
        memberRepository.save(newMember);

        Member memberBeforeWithdrawal = memberRepository.getValidAndUniqueMember("sshyu");

        memberRepository.markAsDeleted(memberBeforeWithdrawal.getMemberId());

        Member memberAfterWithdrawal = memberRepository.getValidAndUniqueMember("sshyu");

        assertEquals(memberBeforeWithdrawal.getIsDeleted(), 0);
        assertEquals(memberAfterWithdrawal.getIsDeleted(), 1);
    }

}
