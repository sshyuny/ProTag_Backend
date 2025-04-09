package com.sshyu.protag.adapter.out.persistence.mysql.member.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Repository
public class MemberJpaRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;

    public void save(MemberEntity memberEntity) {
        em.persist(memberEntity);
    }
}
