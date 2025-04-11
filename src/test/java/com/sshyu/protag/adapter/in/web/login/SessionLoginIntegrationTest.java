package com.sshyu.protag.adapter.in.web.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.out.MemberRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionLoginIntegrationTest {
    
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    MemberRepository memberRepository;

    private ResponseEntity<String> 로그인_요청() {
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

        return responseEntity;
    }

    @Test
    void 로그인_세션생성_정상상황() {

        // given
        memberRepository.save(Member.builder()
            .memberName("ssh")
            .loginId("ssh")
            .password("sshpw1234")
            .build());
        
        // when
        ResponseEntity<String> responseEntity = 로그인_요청();

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        String cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assertTrue(cookie != null ? cookie.startsWith("JSESSIONID") : false);
    }

    @Test
    void 로그인_세션생성_비밀번호틀림() {

        // given
        memberRepository.save(Member.builder()
            .memberName("ssh")
            .loginId("ssh")
            .password("sshpw12345")
            .build());

        // when
        ResponseEntity<String> responseEntity = 로그인_요청();

        // then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    void 로그아웃_인터셉터로_확인() {

        // given
        memberRepository.save(Member.builder()
            .memberName("ssh")
            .loginId("ssh")
            .password("sshpw1234")
            .build());
        
        // 로그인 요청
        ResponseEntity<String> responseEntityOfLoginReq = 로그인_요청();
        String cookieOfLoginReq = responseEntityOfLoginReq.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assertTrue(cookieOfLoginReq != null ? cookieOfLoginReq.startsWith("JSESSIONID") : false);

        // 로그아웃 요청 메시지
        HttpHeaders logoutHeaders = new HttpHeaders();
        logoutHeaders.set(HttpHeaders.COOKIE, cookieOfLoginReq);  // 세션 유지를 위해 쿠키 넣어주기!
        logoutHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntityForLogout = new HttpEntity<>(logoutHeaders);

        // [1] 로그아웃 전
        //when
        ResponseEntity<String> protectedResponseBeforeLogout = restTemplate.exchange(
            "/api/project",
            HttpMethod.GET,
            requestEntityForLogout,
            String.class
        );
        //then
        assertEquals(HttpStatus.OK, protectedResponseBeforeLogout.getStatusCode());

        // [2] 로그아웃
        restTemplate.postForEntity(
            "/api/logout",
            requestEntityForLogout,
            Void.class
        );

        // [3] 로그아웃 후
        // when
        ResponseEntity<String> protectedResponseAfterLogout = restTemplate.exchange(
            "/api/project",
            HttpMethod.GET,
            requestEntityForLogout,
            String.class
        );
        // then
        assertEquals(HttpStatus.UNAUTHORIZED, protectedResponseAfterLogout.getStatusCode());
    }

    @Test
    void 로그아웃_세션으로_확인() {

        // given
        memberRepository.save(Member.builder()
            .memberName("ssh")
            .loginId("ssh")
            .password("sshpw1234")
            .build());
        
        // 로그인 요청
        ResponseEntity<String> responseEntityOfLoginReq = 로그인_요청();
        String cookieOfLoginReq = responseEntityOfLoginReq.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assertTrue(cookieOfLoginReq != null ? cookieOfLoginReq.startsWith("JSESSIONID") : false);

        // 로그아웃 요청 메시지
        HttpHeaders logoutHeaders = new HttpHeaders();
        logoutHeaders.set(HttpHeaders.COOKIE, cookieOfLoginReq);  // 세션이 사라지는걸 확인하기 위해 같이 넣어줌
        logoutHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntityForLogout = new HttpEntity<>(logoutHeaders);

        // when
        ResponseEntity<Void> logoutResponseEntity = restTemplate.postForEntity(
            "/api/logout",
            requestEntityForLogout,
            Void.class
        );

        // then
        String cookieOfLogoutReq = logoutResponseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assertNull(cookieOfLogoutReq);
    }

}
