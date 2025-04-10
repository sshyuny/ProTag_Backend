package com.sshyu.protag.adapter.out.persistence.mysql.member.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDataJpaRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByLoginId(String loginId);

    List<MemberEntity> findByLoginId(String loginId);
    
}
