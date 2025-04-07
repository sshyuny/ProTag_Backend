package com.sshyu.protag.adapter.out.persistence.mysql.project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sshyu.protag.domain.project.model.Project;

@ActiveProfiles("test")
@SpringBootTest
public class ProjectRepositoryAdapterTest {
    
    @Autowired
    ProjectRepositoryRoutingAdapter repositoryAdapter;
    
    @Test
    void Project_저장_조회() {
        repositoryAdapter.saveProject("test");
        List<Project> selectProjects = repositoryAdapter.selectProjects();

        System.out.println("결과 = " + selectProjects.get(0));
    }

}
