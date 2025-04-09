package com.sshyu.protag.adapter.out.persistence.mysql.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sshyu.protag.adapter.out.persistence.mysql.member.MemberRepositoryRoutingAdapter;
import com.sshyu.protag.domain.member.exception.DuplicateLoginIdException;
import com.sshyu.protag.domain.member.model.Member;

@ActiveProfiles("test")
@SpringBootTest
public class MemberRepositoryRoutingAdapterTest {
    
    @Autowired
    MemberRepositoryRoutingAdapter memberRepositoryRoutingAdapter;

    @Test
    void 회원가입_테스트() {

        memberRepositoryRoutingAdapter.validateLoginId("sshyu");

        Member member = Member.builder()
            .memberName("sshyu")
            .loginId("sshyu")
            .password("sshyu1122")
            .build();
        memberRepositoryRoutingAdapter.save(member);

        assertThrows(DuplicateLoginIdException.class, () -> memberRepositoryRoutingAdapter.validateLoginId("sshyu"));

    }
}
