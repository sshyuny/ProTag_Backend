package com.sshyu.protag.adapter.in.web.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.out.MemberRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberIntegrationTest {
    
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원_탈퇴() {

        // given
        memberRepository.save(Member.builder().memberName("ssh").loginId("ssh").password("sshpw1234").build());

        Member memberBeforeWithdrawal = memberRepository.getValidAndUniqueMember("ssh");
        assertEquals(memberBeforeWithdrawal.getIsDeleted(), 0);

        String reqBody = """
            {
                "memberName": "ssh",
                "loginId": "ssh",
                "password": "sshpw1234"
            }
            """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(reqBody, headers);

        // when
        restTemplate.exchange(
            "/api/member",
            HttpMethod.DELETE,
            request,
            Void.class
        );

        // then
        Member memberAfterWithdrawal = memberRepository.getValidAndUniqueMember("ssh");
        assertEquals(memberAfterWithdrawal.getIsDeleted(), 1);
    }

}
