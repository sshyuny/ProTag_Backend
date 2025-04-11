package com.sshyu.protag.adapter.out.persistence.mysql.member.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    private String loginId;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDeleted;

    public void changeForWithdrawal() {
        this.isDeleted = 1;
        this.updatedAt = LocalDateTime.now();
    }

}
