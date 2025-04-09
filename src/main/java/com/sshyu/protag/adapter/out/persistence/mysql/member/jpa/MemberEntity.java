package com.sshyu.protag.adapter.out.persistence.mysql.member.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "MEMBER")
@Getter
@Builder
public class MemberEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    private String loginId;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDeleted;

}
