package com.sshyu.protag.adapter.in.web.project;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.protag.application.port.in.project.ProjectUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sshyu/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectUseCase projectUseCase;
    
    @PostMapping
    public void createProject() {
        projectUseCase.saveProject();
    }

}
