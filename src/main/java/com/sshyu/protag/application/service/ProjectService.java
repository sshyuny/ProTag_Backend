package com.sshyu.protag.application.service;

import org.springframework.stereotype.Service;

import com.sshyu.protag.application.port.in.project.ProjectUseCase;
import com.sshyu.protag.application.port.out.project.ProjectPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectUseCase {

    public final ProjectPort projectPort;
    
    @Override
    public Integer saveProject() {
        return projectPort.saveProject();
    }
}
