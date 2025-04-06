package com.sshyu.protag.adapter.out.persistence.mysql.project.jpa;

import java.time.LocalDateTime;

import com.sshyu.protag.adapter.out.persistence.mysql.member.jpa.MemberEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "PROJECT")
@Getter
public class ProjectEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String title;

    private String descripion;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDeleted;

    protected ProjectEntity() {}

    private ProjectEntity(String title) {
        this.title = title;
    }

    public static ProjectEntity create(String title) {
        return new ProjectEntity(title);
    }

}
