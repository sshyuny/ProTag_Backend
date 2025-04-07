package com.sshyu.protag.adapter.out.persistence.mysql.project.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class ProjectJpaRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveProject(String title) {
        em.persist(ProjectEntity.create(title));
    }
    
}
