package com.sshyu.protag.adapter.out.persistence.mysql.project.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    
    public List<ProjectResult> selectProjects();

}
