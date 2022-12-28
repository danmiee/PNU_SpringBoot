# PNU_SpringBoot
[K-Digital 부산대22-1기] AI 활용 빅데이터분석 풀스택웹서비스 SW 개발자 양성과정

## Chapter

- 수업내용 실습

## Mission
    - 0-1 : 정리하면서 이해하고 작성하기
    - 0-2 : Map 관련 내용 추가 정리하며 이해하고 작성하기
    - 0-3 : 정리 내용 참고하며 작성하기 - 기존 작성본 참조 O
    - 0-4 : 정리 내용 참고하며 작성하기 - 기존 작성본 참조 X
        - 한 군데서 30분 이상 고민해도 안 풀릴 때만 참조하고 강조 표시
    - 0-5 : 정리 내용 보지 않고 작성하기

### Mission 1.
  1. list 데이터를 생성해서 요청하는 정보를 제공하는 버전
  2. 기본 구조는 Controller - Service로 구성되어 있다.
    - MemberController.java
    - MemberService.java
  3. list 데이터는 Service에서 생성해서 가지고 있다가 요청에 따른 데이터를 제공한다.

### Mission 2.
  1. H2 Database에 저장된 데이터를 제공하는 버전
  2. Mission1과 비교해서 Dao 계층이 하나 더 추가된다.
  3. 구조는 Controller - Service - Dao 로 수정된다.
      - MemberController.java
      - MemberService.java
      - MemberDao.java

### Mission 3.
  1. Mission1과 Mission2를 통합한 버전
  2. Mission2의 MemberDao를 MemberDaoH2Impl로 변경하고
  3. MemberDaoH2Impl2에서 MemberDao로 인터페이스를 추출
  4. Mission1의 MemberService에서 list 데이터를 제공하던 것을 MemberDao 구현체인 MemberDaoListImpl을 만들어서 정보를 제공

### Mission 4.
  1. Mission3에서 Dao 객체를 하나 더 추가한 버전
  2. 추가한 Dao는 Log를 남기는 기능을 가지며
  3. 인터페이스 LogDao와 DB 저장 구현체 LogDaoH2Impl과 파일 저장 구현체 LogDaoFileImpl로 구성된다.
  4. 2022.11.25 MemberDaoFileImpl.java가 추가됨.
  
### Mission 5.
  1. Mission4에서 어노테이션을 이용해서 의존성 주입이 되도록 수정한 버전

### Mission 51.
  1. Mission5에서 jdbc Connection을 만들어서 사용하는 방식을
  2. JdbcTemplate를 이용하는 방식으로 변경한 버전

### Mission 6.
  1. Mission5에서 Spring jdbc 를 Spring Data JPA로 변경한 버전
  2. Logging 구조를 SQL문을 저장하지 않고 메서드를 저장하는 방식으로 변경
