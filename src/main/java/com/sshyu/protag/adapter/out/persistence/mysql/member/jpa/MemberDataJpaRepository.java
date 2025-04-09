package com.sshyu.protag.adapter.out.persistence.mysql.member.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDataJpaRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByLoginId(String loginId);
    
}
