package com.sshyu.protag.domain.project.port.out;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sshyu.protag.domain.project.model.Project;
import com.sshyu.protag.domain.project.port.out.project.ProjectRepository;


@ActiveProfiles("test")
@SpringBootTest
public class ProjectRepositoryTest {
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Test
    void Project_저장_조회() {
        // projectRepository.saveProject("test");
        // List<Project> selectProjects = projectRepository.selectProjects();

        // System.out.println("결과 = " + selectProjects.get(0));
    }

}
