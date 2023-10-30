package kiosk.handler;

import kiosk.domain.FoodData;

import java.util.ArrayList;
public class MenuContext {

    Menu menu = new Menu();
    Goods goods = new Goods();
    Order order = new Order();
    ArrayList<FoodData> userFoodData = new ArrayList<>();
    public void start() {
        int type;
        end:
        while(true)
        {
            menu.mainMenu();
            type = menu.getMenuType();
            switch (type) {
                case 0 -> order.totalOrderPrice(); // 판매내역 조회
                case 1 -> { goods.foodsMenu(goods.returnFoodInfo(1));// 버거 메뉴
                            userFoodData = goods.returnUserFood();}
                case 2 -> { goods.foodsMenu(goods.returnFoodInfo(2)); // 아이스크림 메뉴
                            userFoodData = goods.returnUserFood();}
                case 3 -> { goods.foodsMenu(goods.returnFoodInfo(3)); // 음료 메뉴
                            userFoodData = goods.returnUserFood();}
                case 4 -> { goods.foodsMenu(goods.returnFoodInfo(4)); // 맥주 메뉴
                            userFoodData = goods.returnUserFood();}
                case 5 -> order.orders(userFoodData); // 주문메뉴창
                case 6 -> order.orderCancle(userFoodData); // 주문 취소
                case 10 -> { // 종료
                    menu.sc.close();
                    break end;
                }
                default -> System.out.println("잘못 입력하셨습니다. 다시 선택해 주세요."); // 잘못입력 예외 처리
            }
        }
    }

}
