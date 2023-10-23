# Java_Project_Kiosk
## 1. 메뉴판을 보고 주문할 수 있는 Java 프로그램
![image](https://github.com/jshstar/Java_Project_Kiosk/assets/141135747/f9ee1290-29c6-4abe-84be-155443bb0fe7)

코드 동작시 확인 가능한 기능
1. [메인 메뉴판](###1.-메인-메뉴판)
2. [상품 메뉴판](###2.-상품-메뉴판)
3. 상품 선택 및 상품 옵션기능
4. 주문 화면
5. 주문 완료 화면
6. 주문취소 화면
7. 주문 취소시 부분 취소기능
8. 주문 취소시 중복물품 부분 취소 기능
9. 총 판매금액 조회 기능 및 상품 목록 확인 기능



### 1. 메인 메뉴판
```
"SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHAKESHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order       | 장바구니를 확인 후 주문합니다.
6. Cancel      | 진행중인 주문을 취소합니다.
```

### 2. 상품 메뉴판
```
 "SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.

[ Burgers MENU ]
1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
3. Shroom Burger | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거
3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
```

### 3. 상품 선택 및 상품 옵션기능
주문 상품 선택시 Single Double 사이즈 선택가능
```
-------------------------------------------------------
ShackBurger     | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
위 메뉴의 어떤 옵션으로 추가하시겠습니까?
1. Single 6.9     2. Double 10.9 
-------------------------------------------------------
```
장바구니 추가
```
Single을 선택하셨습니다
-------------------------------------------------------
ShackBurger     | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
위 메뉴를 장바구니에 추가하시겠습니까?
1. 확인        2. 취소
-------------------------------------------------------
```

```
ShackBurger가 장바구니에 추가되었습니다.

-------------------------------------------------------
SHAKESHACK BURGER 에 오신걸 환영합니다.
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHACKSHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order      | 장바구니를 확인 후 주문합니다.
6. Cancel     | 진행중인 주문을 취소합니다.
-------------------------------------------------------
```

### 4. 주문화면
메인메뉴에서 5번 Order 선택시 주문한 목록이 표시되며, 중복으로 체크한 상품도 중복개수 표시
```
-------------------------------------------------------
아래와 같이 주문 하시겠습니까?
[ Orders ]
ShackBurger     | W 6.9 | 2개 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
SmokeShack      | W 8.9 | 1개 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
ShackBurger     | W 10.9 | 1개 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
[ Total ]
W 33.6

1. 주문        2. 메뉴판
-------------------------------------------------------
```

### 5.주문완료 화면
```
주문이 완료되었습니다!

대기번호는 [ 1 ] 번 입니다.
(3초후 메뉴판으로 돌아갑니다.)
```
```
"SHAKESHACK BURGER 에 오신걸 환영합니다."
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHAKESHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order       | 장바구니를 확인 후 주문합니다.
6. Cancel      | 진행중인 주문을 취소합니다.
```

### 6. 주문취소 화면
```
진행하던 주문을 취소하시겠습니까?
1. 확인        2. 장바구니 부분 취소        3.취소
```
1번 확인 입력시
```
진행하던 주문을 취소하시겠습니까?
1. 확인        2. 장바구니 부분 취소        3.취소
1

진행하던 주문이 취소되었습니다.
-------------------------------------------------------
SHAKESHACK BURGER 에 오신걸 환영합니다.
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHACKSHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order      | 장바구니를 확인 후 주문합니다.
6. Cancel     | 진행중인 주문을 취소합니다.
-------------------------------------------------------
```

### 7. 주문 취소시 부분 취소기능

2번 장바구니 부분 취소 입력시
```
진행하던 주문을 취소하시겠습니까?
1. 확인        2. 장바구니 부분 취소        3.취소
2
-------------------------------------------------------
주문 목록
1. ShackBurger     | W 6.9 | 1개 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
2. SmokeShack      | W 8.9 | 1개 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
-------------------------------------------------------
-------------------------------------------------------
취소하실 음식을 입력해주세요
취소 목록
취소완료: 0
-------------------------------------------------------
```

취소하고 싶은 음식을 선택
```
-------------------------------------------------------
취소하실 음식을 입력해주세요
취소 목록
취소완료: 0
-------------------------------------------------------
1
-------------------------------------------------------
주문 목록
2. SmokeShack      | W 8.9 | 1개 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
-------------------------------------------------------
-------------------------------------------------------
취소하실 음식을 입력해주세요
취소 목록
1. ShackBurger     | 1개 
취소완료: 0
-------------------------------------------------------
```

취소완료시
```
-------------------------------------------------------
취소하실 음식 목록
1. ShackBurger     | W 6.9 | 1개 
취소하시겠습니까?
1. 확인        2. 취소
-------------------------------------------------------
1
선택하신 상품이 취소되었습니다.
-------------------------------------------------------
SHAKESHACK BURGER 에 오신걸 환영합니다.
아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ SHACKSHACK MENU ]
1. Burgers         | 앵거스 비프 통살을 다져만든 버거
2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림
3. Drinks          | 매장에서 직접 만드는 음료
4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주

[ ORDER MENU ]
5. Order      | 장바구니를 확인 후 주문합니다.
6. Cancel     | 진행중인 주문을 취소합니다.
-------------------------------------------------------
```
확인 입력시 장바구니 목록에서 취소되며 취소를 입력시 메인메뉴로 돌아가서 다시 선택

### 8. 주문 취소시 중복물품 부분 취소 기능

부분 취소와 전부 취소가 표시되며 1번 부분취소를 입력시 선택한 물품 개수안에서 원하는 개수만큼 취소가능
1. 입력을 입력시 취소목록에 추가 2.재입력 입력시 다시 취소 물품 개수 입력
0번 취소완료를 통해 취소목록 확인

1번입력
```
-------------------------------------------------------
1개 취소를 입력하셨습니다.
입력 하시겠습니까?
1. 입력        2.재입력
-------------------------------------------------------
1
-------------------------------------------------------
주문 목록
2. Shroom Burger   | W 9.4 | 1개 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거
3. Red Bean Shake  | W 6.5 | 1개 | 신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크
-------------------------------------------------------
-------------------------------------------------------
취소하실 음식을 입력해주세요
취소 목록
1. ShackBurger     | 1개 
취소완료: 0
-------------------------------------------------------
0
-------------------------------------------------------
취소하실 음식 목록
1. ShackBurger     | W 6.9 | 1개 
취소하시겠습니까?
1. 확인        2. 취소
-------------------------------------------------------
```


2번 입력



2번 입력후 확인시 취소완료와 함께 메인메뉴로 돌아간다.
```
-------------------------------------------------------
1개 취소를 입력하셨습니다.
입력 하시겠습니까?
1. 입력        2.재입력
-------------------------------------------------------
2
개수를 입력해주세요.
1
-------------------------------------------------------
1개 취소를 입력하셨습니다.
입력 하시겠습니까?
1. 입력        2.재입력
-------------------------------------------------------
1
-------------------------------------------------------
주문 목록
2. Shroom Burger   | W 9.4 | 1개 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거
3. Red Bean Shake  | W 6.5 | 1개 | 신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크
-------------------------------------------------------
-------------------------------------------------------
취소하실 음식을 입력해주세요
취소 목록
1. ShackBurger     | 1개 
취소완료: 0
-------------------------------------------------------
0
-------------------------------------------------------
취소하실 음식 목록
1. ShackBurger     | W 6.9 | 1개 
취소하시겠습니까?
1. 확인        2. 취소
-------------------------------------------------------
```
## 9. 총 판매금액 조회 기능 및 상품 목록 확인 기능

메인메뉴에서 0번 입력시 이동가능
```
-------------------------------------------------------
[ 총 판매상품 목록 현황 ]
현재까지 총 판매된 상품 목록은 아래와 같습니다.
- ShackBurger | W 6.9 
- ShackBurger | W 6.9 
- Shroom Burger | W 9.4 
- Red Bean Shake | W 6.5 

[ 총 판매금액 현황 ]
현재까지 총 판매된 금액은 [ W 29.7 ] 입니다

1. 돌아가기
-------------------------------------------------------
```




