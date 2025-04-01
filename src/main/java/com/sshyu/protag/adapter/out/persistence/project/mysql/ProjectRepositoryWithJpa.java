package com.sshyu.protag.adapter.out.persistence.project.mysql;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@Transactional
public class ProjectRepositoryWithJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveProject(String title) {
        em.persist(ProjectEntity.create(title));
    }
    
}
