# 배경
- 프로젝트의 로그인 방식을 점차 변경 및 확장함

# 진행 과정
## 1. 기본 로컬 로그인 방법
- 사용 기술: 스프링에서 기본으로 제공하는 Session
- 관련 테이블: MEMBER
```sql
CREATE TABLE MEMBER (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(50) COMMENT '사용자 이름',
    login_id VARCHAR(100) COMMENT '로그인 아이디', -- 로그인용
    password VARCHAR(255) COMMENT '로그인 비밀번호', -- 로그인용
    created_at DATETIME NOT NULL COMMENT '데이터 생성 시각',
    updated_at DATETIME NOT NULL COMMENT '데이터 수정 시각',
    is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부(0: 존재, 1: 삭제)'
);
```
- 기능
    - 회원 가입
    - 로그인(세션 생성)
    - 로그아웃(세션 삭제)
    - 인터셉터에서 회원 전용 경로 설정 추가

