package com.sshyu.protag.adapter.out.persistence.project.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ProjectEntity {
    
    @Id @GeneratedValue
    private Long id;

    private String title;

    protected ProjectEntity() {}

    private ProjectEntity(String title) {
        this.title = title;
    }

    public static ProjectEntity create(String title) {
        return new ProjectEntity(title);
    }

}
