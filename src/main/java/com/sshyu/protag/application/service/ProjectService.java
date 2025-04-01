package com.sshyu.protag.application.service;

import org.springframework.stereotype.Service;

import com.sshyu.protag.domain.project.port.in.project.ProjectUseCase;
import com.sshyu.protag.domain.project.port.out.project.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectUseCase {

    private final ProjectRepository projectRepository;
    
    @Override
    public void saveProject(String title) {
        projectRepository.saveProject(title);
    }
}
