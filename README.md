# Parking

> 주차 차량 관제

## Index

- [Parking](#parking)
  - [Index](#index)
  - [Branch 전략](#branch-전략)
  - [브랜치별 진행상황 정리](#브랜치별-진행상황-정리)
    - [app-1](#app-1)
    - [app-2](#app-2)

## Branch 전략

> Default로 최대한 GitFlow를 따른다.

## 로직
### 관리 데이터 리스트
- id : Long
- CarNumber : String
- PhoneNumber : String
- NearInTime\[최근 입차시간\] : LocalDateTime
- Amount : Int
- isPayment : Boolean
### 행위
1. 입차
2. 출차
3. 출력
4. 수정
5. 출차
6. 종료

## 브랜치별 진행상황 정리

### app-1

> Hello World

### app-2
> 리터럴을 활용한 데이터 출력을 진행하였다

### app-3
> 변수를 활용하여 데이터의 표기 시작

### app-4
> 각 항목들을 Scanner를 통해 입력받아 데이터 표기 진행
> 직접 기입하는 항목은 차량번호, 핸드폰 번호이다