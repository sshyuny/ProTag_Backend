package com.sshyu.protag.domain.project.port.out.project;

import java.util.List;

import com.sshyu.protag.domain.project.model.Project;

public interface ProjectRepository {
    
    void saveProject(String title);

    List<Project> selectProjects();
    
}
