# KDigitalPNUMission
빅데이터웹분석개발자과정 SpringBoot Mission Project

Mission 1.
  1. list 데이터를 생성해서 요청하는 정보를 제공하는 버전
  2. 기본 구조는 Controller - Service로 구성되어 있다.
    - MemberController.java
    - MemberService.java
  3. list 데이터는 Service에서 생성해서 가지고 있다가 요청에 따른 데이터를 제공한다.

Mission 2.
  1. H2 Database에 저장된 데이터를 제공하는 버전
  2. Mission1과 비교해서 Dao 계층이 하나 더 추가된다.
  3. 구조는 Controller - Service - Dao 로 수정된다.
      - MemberController.java
      - MemberService.java
      - MemberDAO.java

Mission 3.
  1. Mission1과 Mission2를 통합한 버전
  2. Mission2의 MemberDAO를 MemberDaoH2Impl로 변경하고
  3. MemberDaoH2Impl에서 MemberDAO로 인터페이스를 추출
  4. Mission1의 MemberService에서 list 데이터를 제공하던 것을 MemberDAO 구현체인 MemberDaoListImpl을 만들어서 정보를 제공
      - Mission1과 Mission2의 id 타입이 달라서 Mission2의 타입인 String으로 통일

Mission 4.
  1. Mission3에서 Dao 객체를 하나 더 추가한 버전
  2. 추가한 Dao는 Log를 남기는 기능을 가지며
  3. 인터페이스 LogDao와 DB 저장 구현체 LogDaoH2Impl과 파일 저장 구현체 LogDaoFileImpl로 구성된다.
      - 현재 LogDaoH2Impl 내용만 LogDAO클래스로 작성된 상태로 인터페이스 추출 및 LogDaoFileImpl 추가 필요
  
Mission 5.
  1. Mission4에서 어노테이션을 이용해서 의존성 주입이 되도록 수정한 버전
