package com.sshyu.protag.adapter.in.web.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sshyu.protag.adapter.in.web.project.ProjectController;
import com.sshyu.protag.application.manager.SessionConst;
import com.sshyu.protag.domain.project.port.in.project.ProjectUseCase;


@WebMvcTest(ProjectController.class)
public class SessionLoginControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProjectUseCase projectUseCase;

    @Test
    void 비로그인_상태에서_접근() throws Exception {
        mockMvc.perform(get("/api/project"))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    @Test
    void 로그인_상태에서_접근() throws Exception {

        // 세션 생성
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, "sshyu");

        // 접근
        MvcResult response = mockMvc.perform(get("/api/project")
                .session(session))
            .andExpect(status().isOk())
            .andReturn();

        String contentAsString = response.getResponse().getContentAsString();
        System.out.println("응답 바디 = " + contentAsString);
    }

}
