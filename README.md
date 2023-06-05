# Parking

> 주차 차량 관제

## Index

- [Parking](#parking)
  - [Index](#index)
  - [Branch 전략](#branch-전략)
  - [브랜치별 진행상황 정리](#브랜치별-진행상황-정리)
    - [app v1.0](#app-v10)

## Branch 전략

> Default로 최대한 GitFlow를 따른다.

## 로직
### 관리 데이터 리스트
- id : Long
- CarNumber : String
- PhoneNumber : String
- NearInTime\[최근 입차시간\] : LocalDateTime
- Amount : Int
### 행위
1. 입차
2. 출차
3. 출력
4. 수정
5. 출차
6. 종료

## 브랜치별 진행상황 정리

### app v1.0

> Hello World
