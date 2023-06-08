# Parking

> 주차 차량 관제

## Index

- [Parking](#parking)
  - [Index](#index)
  - [Branch 전략](#branch-전략)
  - [브랜치별 진행상황 정리](#브랜치별-진행상황-정리)
    - [app-1](#app-1)
    - [app-2](#app-2)
    - [app-3](#app-3)
    - [app-4](#app-4)
    - [app-5](#app-5)
    - [app-6](#app-6)
    - [app-7](#app-7)
    - [app-8](#app-8)
    - [app-9](#app-9)
    - [app-10](#app-10)

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

### app-5
> 배열, 반복문을 활용한 데이터 복수개 입력 및 표기

### app-6
> 조건문을 활용한 데이터 입력 흐름 제어 추가

### app-7
> 기능을 메서듣 단위로 분할

### app-8
> 기존에 `main`에서 모든 변수의 관리를 하던 것을 static으로 메모리 등재해서 전 변수에서 읽도록 수정

### app-9
> 클래스를 활용해 기존의 기능 클래스로 분류

### app-10
> Map을 통한 메뉴 구성 및, Thread로 실행하는 방식활용, DTO를 활용한 CRUD