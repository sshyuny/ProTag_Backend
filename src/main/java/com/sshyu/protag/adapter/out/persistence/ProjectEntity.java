package com.sshyu.protag.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor
@Getter
public class ProjectEntity {
    
    @Id @GeneratedValue
    private Long id;

    private String title;

    protected ProjectEntity() {}

}
