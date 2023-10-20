package kiosk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Goods extends Menu {

    // 음식 Data
    private static final ArrayList<FoodData> BUGERS_LIST = new ArrayList<>(Arrays.asList(
            new FoodData("ShackBurger", new double[]{6.9,10.9}, "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
            , new FoodData("SmokeShack", new double[]{8.9,12.9}, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거")
            , new FoodData("Shroom Burger",new double[]{9.4,12.4} , "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거")
            , new FoodData("Cheeseburger", new double[]{6.9,10.9}, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거")
            , new FoodData("Hamburger", new double[]{5.4,9.0}, "비프패티를 기반으로 야채가 들어간 기본버거")));

    private static final ArrayList<FoodData> FROZEN_CUSTARD = new ArrayList<>(Arrays.asList(
            new FoodData("Shacke of the week", new double[]{6.5,0}, "특별한 커스터드 플레이버")
            , new FoodData("Red Bean Shake", new double[]{6.5,0}, "신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크")
            , new FoodData("Floats", new double[]{5.9,0}, "루트 비어, 퍼플 카우, 크림시클")
            , new FoodData("Cup & Cone", new double[]{5.9,8.9}, "바닐라, 초콜렛")));

    private static final ArrayList<FoodData> DRINK =new ArrayList<>(Arrays.asList(
            new FoodData("Shack-made Lemonade", new double[]{3.9,4.5}, "매장에서 직접 만드는 상큼한 레몬에이드(오리지날/시즈널)")
            , new FoodData("Fresh Brewed Iced Tea", new double[]{3.4,3.9}, "직접 유기농 홍차를 우려낸 아이스티")
            , new FoodData("Fifty/Fifty", new double[]{3.5,4.4}, "레몬에이드와 아이스티의 만남")
            , new FoodData("Fountain Soda", new double[]{2.7,3.3}, "코카콜라, 코카콜라 제로, 스프라이트, 환타오렌지, 환타 그레이프")
            , new FoodData("Abita Root Beer", new double[]{4.4,0}, "청량감 있는 독특한 미국식 무알콜 탄산음료")
            , new FoodData("Bottled Water", new double[]{1.0,0}, "지리산 암반대수층으로 만든 프리미엄 생수")));
    private static final ArrayList<FoodData> BEER = new ArrayList<>(Arrays.asList(
            new FoodData("ShackMeister Ale", new double[]{8.9,0}, "쉐이크쉨 버거를 위해 뉴옥 브루클린 브루어리에서 특별히 양조한 에일 맥주")));


    private ArrayList<FoodData> orderBasket = new ArrayList<>(); // 유저 장바구니 객체
    private Order orderInfo = new Order(); // OrderData 객체


    public void start() {
        int type;
        end:
        while(true)
        {
            mainMenu();
            type = getMenuType();
            switch (type) {
                case 0 -> orderInfo.totalOrderPrice(); // 판매내역 조회
                case 1 -> foodsMenu(BUGERS_LIST); // 버거 메뉴
                case 2 -> foodsMenu(FROZEN_CUSTARD); // 아이스크림 메뉴
                case 3 -> foodsMenu(DRINK); // 음료 메뉴
                case 4 -> foodsMenu(BEER); // 맥주 메뉴
                case 5 -> orderInfo.orders(orderBasket); // 주문메뉴창
                case 6 -> orderInfo.orderCancle(orderBasket); // 주문 취소
                case 10 -> { // 종료
                    sc.close();
                    break end;
                }
                default -> System.out.println("잘못 입력하셨습니다. 다시 선택해 주세요."); // 잘못입력 예외 처리
            }
        }
    }


    // 음식 메뉴창
    public void foodsMenu(ArrayList<FoodData> foodList)
    {
        int foodtype = getMenuType();
        int chooseNum;
        System.out.println("-------------------------------------------------------");
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println();
        System.out.println(FOODTITLE[foodtype-1]);
        // 음식 Data 출력
        for (int i = 0; i <  foodList.size(); i++) {
            System.out.printf("%d. %-15s | W %.1f | %s%n"
                    , i + 1, foodList.get(i).getFoodName(),foodList.get(i).getChosePrice()[0], foodList.get(i).getExplain());
        }
        System.out.println("-------------------------------------------------------");
        try // Scanner 예외처리
        {
            chooseNum = sc.nextInt();
            sc.nextLine();
            if(chooseNum< foodList.size() && chooseNum >0)
                shoppingBasket(foodList,chooseNum);
            else{
                System.out.println("잘못된 숫자 정보입니다 다시입력해주세요");
                foodsMenu(foodList);
            }
        }
        catch (InputMismatchException e){
            System.out.println("잘못된 숫자를 입력하셨습니다 다시 입력해주세요");
            sc.nextLine();
            foodsMenu(foodList);
        }
    }

    // 주문 확인정보창
    public void shoppingBasket(ArrayList<FoodData> foodList, int foodType)
    {
        String addtype;
        int size =0;
        int index = foodType-1;
        if(foodList.get(index).getChosePrice()[1] !=0)
            size = chooseSize(foodList, index);
        String name = foodList.get(index).getFoodName();
        String explain = foodList.get(index).getExplain();
        double price = foodList.get(index).getChosePrice()[size];
        // 출력
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-15s | W %.1f | %s%n"
                ,foodList.get(index).getFoodName()
                ,foodList.get(index).getChosePrice()[size]
                , foodList.get(index).getExplain());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        System.out.println("-------------------------------------------------------");
        addtype = sc.nextLine();
        // 장바구니 추가시 유저의 장바구니에 데이터 입력
        if(addtype.equals("1"))
        {
            System.out.println(foodList.get(index).getFoodName() + "가 장바구니에 추가되었습니다.");
            System.out.println();
            if(!orderBasket.isEmpty())
            {
                boolean flag = false;
                for (int i = 0; i < orderBasket.size(); i++) {
                    if(orderBasket.get(i).getFoodName().equals(foodList.get(index).foodname)
                            && orderBasket.get(i).getPrice() == foodList.get(index).chosePrice[size]  )
                    {
                        orderBasket.get(index).setFoodCount(1);
                        flag =true;
                    }
                }
                if(!flag)orderBasket.add(new FoodData(name,price,explain,1));
            }
            else orderBasket.add(new FoodData(name,price,explain,1));
        }
        else if(addtype.equals("2"))
        {
            System.out.println("취소되었습니다.");
        }
        else{
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            shoppingBasket(foodList,foodType);
        }
    }

    // 사이즈 선택메뉴
    public int chooseSize(ArrayList<FoodData> foodData,int index){
        int chooseType =0;
        System.out.println("-------------------------------------------------------");

        System.out.printf("%-15s | W %.1f | %s%n"
                ,foodData.get(index).getFoodName()
                ,foodData.get(index).getChosePrice()[0]
                , foodData.get(index).getExplain());
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.printf("1. Single %.1f     2. double %.1f %n"
                , foodData.get(index).getChosePrice()[0],foodData.get(index).getChosePrice()[1]);
        System.out.println("-------------------------------------------------------");
        String type = sc.nextLine();
        if(type.equals("1"))
        {
            System.out.println("Single을 선택하셨습니다");
            chooseType =0;
        }
        else if (type.equals("2"))
        {
            System.out.println("Double을 선택하셨습니다");
            chooseType =1;
        }
        else
        {
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            chooseSize(foodData,index);
        }
        return chooseType;
    }
}
