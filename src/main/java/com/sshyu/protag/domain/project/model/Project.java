package com.sshyu.protag.domain.project.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder @Getter
@ToString
public class Project {
    
    private Long id;
    private String title;

}
