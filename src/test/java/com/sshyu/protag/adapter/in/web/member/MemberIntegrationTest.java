package com.sshyu.protag.adapter.in.web.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.out.MemberRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberIntegrationTest {
    
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 로그인_세션생성_정상상황() {
        memberRepository.save(Member.builder().memberName("ssh").loginId("ssh").password("sshpw1234").build());

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
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/login", request, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        System.out.println(cookies.get(0));
    }

    @Test
    void 로그인_세션생성_비밀번호틀림() {
        memberRepository.save(Member.builder().memberName("ssh").loginId("ssh").password("sshpw1234").build());

        String reqBody = """
            {
                "memberName": "ssh",
                "loginId": "ssh",
                "password": "sshpw12345"
            }
            """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(reqBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/login", request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
