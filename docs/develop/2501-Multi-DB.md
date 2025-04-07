# 배경
### 프로젝트 설정
- 여러 데이터베이스(MySQL, PostgreSQL)와 데이터베이스 접근 방식(JPA, MyBatis) 사용
- 헥사고날 아키텍처 사용
### 핵심 목표
- 다중 데이터베이스와 다중 데이터베이스 접근방식을 사용할 때, 헥사고날 아키텍처를 깔끔하게 구성하자!

# 고민 포인트와 해결 방법

## 1. adapter와 port의 Repository 구성

### 1.1 고민 포인트
- adapter 아래 Repository는 port 아래의 Repository 인터페이스를 구현해야 함
- adapter 아래에는 Repository가 여러 개 존재해서 JPA와 MyBatis를 각각 사용해야 함.

### 1.2. 해결 방법
- adapter 아래에 JPA와 MyBatis를 사용하는 각각의 Repository 생성(JpaRepositoryImpl, MyBatisRepositoryImpl).
- 각 Repository를 하나로 묶어주는 역할의 RouterRepository를 만들고 이 RouterRepository가 port 아래의 Repository를 구현!

### 1.3. 패키지 구성
```
adapter / out / persistence /
    ⌙ member /
        ⌙ jpa / 
            ⌙ MemberJpaRepositoryImpl.java
        ⌙ mybatis /
            ⌙ MemberMyBatisRepositoryImpl.java
        ⌙ MemberRepositoryRoutingAdapter.java
```

## 2. adapter의 out단 패키지 구조

### 2.1. 고민 포인트
- adapter/out/persistence 아래에 패키지를 구성할 때, 도메인 별로 분리 vs. 데이터베이스별로 분리, 어떤게 유리할까?

### 2.2. 해결 방법
- 데이터베이스가 분리됨을 명확히 표현하기 위해 먼저 데이터베이스별로 분리하고, 그 아래에서 도메인별로 나누기로 결정!

### 2.3. 패키지 구성
```
adapter / out / persistence /
    ⌙ mysql /
        ⌙ member /
        ⌙ project /
    ⌙ postgre
```
