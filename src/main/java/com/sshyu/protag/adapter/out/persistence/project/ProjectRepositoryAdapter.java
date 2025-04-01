package com.sshyu.protag.adapter.out.persistence.project;

import org.springframework.stereotype.Component;

import com.sshyu.protag.adapter.out.persistence.project.mysql.ProjectRepositoryWithJpa;
import com.sshyu.protag.domain.project.port.out.project.ProjectRepository;

import lombok.RequiredArgsConstructor;

/*
 * 통합 OUT단 클래스
 * 다양한 DB 환경 및 DB 접근 방식을 관리
 */
@RequiredArgsConstructor
@Component
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectRepositoryWithJpa projectRepositoryWithJpa;
    
    @Override
    public void saveProject(String title) {
        projectRepositoryWithJpa.saveProject(title);
    }

}
