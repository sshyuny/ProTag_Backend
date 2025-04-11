package com.sshyu.protag.adapter.in.web.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.protag.domain.project.port.in.project.ProjectUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectUseCase projectUseCase;
    
    @PostMapping
    public void createProject(@RequestBody String title) {
        projectUseCase.saveProject(title);
    }

    @GetMapping
    public String get() {
        return "success";
    }

}
