# 배경
- 프로젝트의 로그인 방식을 점차 변경 및 확장함

# 진행 과정
## 1. 기본 로컬 로그인 방법
### 1.1. 기본 내용
- 사용 기술: 스프링에서 기본으로 제공하는 Session
- 기능
    - 회원 가입, 회원 탈퇴
    - 로그인(세션 생성), 로그아웃(세션 삭제)
    - 인터셉터에서 회원 전용 경로 설정 추가
### 1.2. 테이블
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
### 1.3. 아키텍처
- 고민 포인트
    - 이후 JWT 등 다른 방식으로 변경할 것을 대비하고 싶음
    - UseCase단에서 HttpSession 기술을 바라보지 않도록 하고 싶음
- 해결 방법
    - SessionManager 인터페이스에서 세션 처리 하도록 함

