package com.sshyu.protag.adapter.out.persistence.mysql.member;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sshyu.protag.adapter.out.persistence.mysql.member.jpa.MemberDataJpaRepository;
import com.sshyu.protag.adapter.out.persistence.mysql.member.jpa.MemberEntity;
import com.sshyu.protag.adapter.out.persistence.mysql.member.jpa.MemberJpaRepositoryImpl;
import com.sshyu.protag.domain.member.exception.DuplicateLoginIdException;
import com.sshyu.protag.domain.member.exception.InvalidLoginException;
import com.sshyu.protag.domain.member.model.Member;
import com.sshyu.protag.domain.member.port.out.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Transactional
public class MemberRepositoryRoutingAdapter implements MemberRepository {
    
    final MemberJpaRepositoryImpl memberJpaRepositoryImpl;
    final MemberDataJpaRepository memberDataJpaRepository;

    @Override
    public void save(Member member) {
        LocalDateTime now = LocalDateTime.now();
        MemberEntity memberEntity = MemberEntity.builder()
            .memberName(member.getMemberName())
            .loginId(member.getLoginId())
            .password(member.getPassword())
            .createdAt(now)
            .updatedAt(now)
            .isDeleted(0)
            .build();
        memberJpaRepositoryImpl.save(memberEntity);
    }

    @Override
    public void validateLoginId(String loginId) {
        boolean isLoginIdInUse = memberDataJpaRepository.existsByLoginId(loginId);
        if (isLoginIdInUse) { throw new DuplicateLoginIdException("이미 존재하는 아이디입니다."); }
    }

    @Override
    public Member getValidAndUniqueMember(String loginId) {
        List<MemberEntity> memberEntities = memberDataJpaRepository.findByLoginId(loginId);

        int memberEntitiesSize = memberEntities.size();
        if (memberEntitiesSize < 1) { throw new InvalidLoginException("존재하지 않는 loginId: " + loginId); }
        if (memberEntitiesSize > 1) { throw new InvalidLoginException("여러 loginId 존재: " + loginId); }

        MemberEntity memberEntity = memberEntities.get(0);

        return Member.builder()
                    .memberId(memberEntity.getMemberId())
                    .loginId(memberEntity.getLoginId())
                    .password(memberEntity.getPassword())
                    .memberName(memberEntity.getMemberName())
                    .isDeleted(memberEntity.getIsDeleted())
                    .build();
    }

    @Override
    public void markAsDeleted(Long memberId) {
        memberJpaRepositoryImpl.updateForWithdrawal(memberId);
    }

}
