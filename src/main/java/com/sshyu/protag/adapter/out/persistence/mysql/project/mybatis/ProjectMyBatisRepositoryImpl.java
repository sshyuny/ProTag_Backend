package com.sshyu.protag.adapter.out.persistence.mysql.project.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectMyBatisRepositoryImpl {
    
    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectResult> selectProjects() {
        return projectMapper.selectProjects();
    }

}
