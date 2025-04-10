package com.sshyu.protag.application.manager;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import com.sshyu.protag.domain.auth.model.SessionToken;
import com.sshyu.protag.domain.auth.port.in.SessionManager;
import com.sshyu.protag.domain.member.model.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HttpSessionManager implements SessionManager {

    private final ObjectProvider<HttpSession> httpSessionProvider;
    
    @Override
    public SessionToken createSessionFor(Member member) {

        HttpSession httpSession = httpSessionProvider.getObject();
        httpSession.setAttribute("LOGIN", member);

        return SessionToken.builder().token("LOGIN").build();
    }
}
