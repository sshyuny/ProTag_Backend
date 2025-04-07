package com.sshyu.protag.adapter.out.persistence.mysql.project;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sshyu.protag.adapter.out.persistence.mysql.project.jpa.ProjectJpaRepositoryImpl;
import com.sshyu.protag.adapter.out.persistence.mysql.project.mybatis.ProjectMyBatisRepositoryImpl;
import com.sshyu.protag.adapter.out.persistence.mysql.project.mybatis.ProjectResult;
import com.sshyu.protag.domain.project.model.Project;
import com.sshyu.protag.domain.project.port.out.project.ProjectRepository;

import lombok.RequiredArgsConstructor;

/*
 * Port Repository 직접 구현!
 * 여러 DB 환경 및 DB 접근 방식을 관리하기 위해 각 repository를 사용하는 해당 통합 repository 둠!
 */
@RequiredArgsConstructor
@Component
@Transactional
public class ProjectRepositoryRoutingAdapter implements ProjectRepository {

    private final ProjectJpaRepositoryImpl projectRepositoryWithJpa;
    private final ProjectMyBatisRepositoryImpl projectRepositoryWithMyBatis;
    
    @Override
    public void saveProject(String title) {
        projectRepositoryWithJpa.saveProject(title);
    }

    @Override
    public List<Project> selectProjects() {
        List<ProjectResult> selectProjects = projectRepositoryWithMyBatis.selectProjects();
        return selectProjects.stream()
                    .map(result -> Project.builder().title(result.getTitle()).id(result.getId()).build())
                    .toList();
    }

}
