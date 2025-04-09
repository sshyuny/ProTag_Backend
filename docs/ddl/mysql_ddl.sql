/* MySQL */

-- DATABASE
DROP DATABASE IF EXISTS project_db;
CREATE DATABASE project_db;
USE project_db;

-- USERS
DROP USER IF EXISTS 'pjappuser'@'localhost', 'pjadminuser'@'localhost';
CREATE USER 'pjadminuser'@'localhost' IDENTIFIED BY 'pw11';
CREATE USER 'pjappuser'@'localhost' IDENTIFIED BY 'pw11';
GRANT ALL PRIVILEGES ON project_db.* TO 'pjadminuser'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON project_db.* TO 'pjappuser'@'localhost';
FLUSH PRIVILEGES;

exit
mysql -u pjadminuser -p

-- TABLES
DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(50) COMMENT '사용자 이름',
    login_id VARCHAR(100) COMMENT '로그인 아이디', -- 로그인용
    password VARCHAR(255) COMMENT '로그인 비밀번호', -- 로그인용
    created_at DATETIME NOT NULL COMMENT '데이터 생성 시각',
    updated_at DATETIME NOT NULL COMMENT '데이터 수정 시각',
    is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부(0: 존재, 1: 삭제)'
);

DROP TABLE IF EXISTS PROJECT;
CREATE TABLE PROJECT (
    project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    title VARCHAR(100) NOT NULL COMMENT '프로젝트 제목',
    description VARCHAR(255) COMMENT '프로젝트에 대한 간단한 설명',
    created_at DATETIME NOT NULL COMMENT '데이터 생성 시각',
    updated_at DATETIME NOT NULL COMMENT '데이터 수정 시각',
    is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부(0: 존재, 1: 삭제)',
    FOREIGN KEY (member_id) REFERENCES MEMBER(member_id)
);


