package kiosk.handler;

import kiosk.domain.ChosePrice;
import kiosk.domain.FoodData;
import java.util.ArrayList;
import java.util.Arrays;

public class Goods extends Menu {
    private static final ArrayList<FoodData> BURGERS_LIST = new ArrayList<>(Arrays.asList(
            new FoodData("ShackBurger", new ChosePrice(6.9,10.9), "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
            , new FoodData("SmokeShack", new ChosePrice(8.9,12.9), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거")
            , new FoodData("Shroom Burger",new ChosePrice(9.4,12.4) , "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거")
            , new FoodData("Cheeseburger", new ChosePrice(6.9,10.9), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거")
            , new FoodData("Hamburger", new ChosePrice(5.4,9.0), "비프패티를 기반으로 야채가 들어간 기본버거")));

    private static final ArrayList<FoodData> FROZEN_CUSTARD = new ArrayList<>(Arrays.asList(
            new FoodData("Shacke of the week", new ChosePrice(6.5,0), "특별한 커스터드 플레이버")
            , new FoodData("Red Bean Shake", new ChosePrice(6.5,0), "신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크")
            , new FoodData("Floats", new ChosePrice(5.9,0), "루트 비어, 퍼플 카우, 크림시클")
            , new FoodData("Cup & Cone", new ChosePrice(5.9,8.9), "바닐라, 초콜렛")));

    private static final ArrayList<FoodData> DRINK =new ArrayList<>(Arrays.asList(
            new FoodData("Shack-made Lemonade",new ChosePrice(3.9,4.5), "매장에서 직접 만드는 상큼한 레몬에이드(오리지날/시즈널)")
            , new FoodData("Fresh Brewed Iced Tea",new ChosePrice(3.4,3.9), "직접 유기농 홍차를 우려낸 아이스티")
            , new FoodData("Fifty/Fifty", new ChosePrice(3.5,4.4), "레몬에이드와 아이스티의 만남")
            , new FoodData("Fountain Soda", new ChosePrice(2.7,3.3), "코카콜라, 코카콜라 제로, 스프라이트, 환타오렌지, 환타 그레이프")
            , new FoodData("Abita Root Beer", new ChosePrice(4.4,0), "청량감 있는 독특한 미국식 무알콜 탄산음료")
            , new FoodData("Bottled Water", new ChosePrice(1.0,0), "지리산 암반대수층으로 만든 프리미엄 생수")));
    private static final ArrayList<FoodData> BEER = new ArrayList<>(Arrays.asList(
            new FoodData("ShackMeister Ale", new ChosePrice(8.9,0), "쉐이크쉨 버거를 위해 뉴옥 브루클린 브루어리에서 특별히 양조한 에일 맥주")));


    private ArrayList<FoodData> orderBasket = new ArrayList<>(); // 유저 장바구니 객체


    public ArrayList<FoodData> returnFoodInfo(int num){
        ArrayList<FoodData> food;
        switch(num)
        {
            case 1 -> {food = BURGERS_LIST;}
            case 2-> {food = FROZEN_CUSTARD;}
            case 3->{food = DRINK;}
            case 4->{food = BEER;}
            default -> food=null;
        }
        return food;
    }

    public ArrayList<FoodData> returnUserFood(){
        return orderBasket;
    }
    // 음식 메뉴창
    public void foodsMenu(ArrayList<FoodData> foodList)
    {
        int chooseNum;
        System.out.println("-------------------------------------------------------");
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println();
        System.out.println(getMenuType());
        System.out.println(super.FOODTITLE[getMenuType()-1]);
        // 음식 Data 출력
        for (int i = 0; i <  foodList.size(); i++) {
            System.out.printf("%d. %-15s | W %.1f | %s%n"
                    , i + 1, foodList.get(i).getFoodName(),foodList.get(i).getChosePrice().getSingleSize(), foodList.get(i).getExplain());
        }
        System.out.println("-------------------------------------------------------");


        while (!sc.hasNextInt())
        {
            sc.next();
            System.out.println("문자를 입력하셨습니다. 다시 입력해주세요");
        }
        chooseNum = sc.nextInt();
        sc.nextLine();
        if(chooseNum< foodList.size()+1 && chooseNum >0)
            shoppingBasket(foodList,chooseNum);
        else{
            System.out.println("잘못된 숫자를 입력하셨습니다. 숫자로 다시 입력해주세요");
            foodsMenu(foodList);
        }

    }

    // 주문 확인정보창
    public void shoppingBasket(ArrayList<FoodData> foodList, int foodType)
    {
        String addtype;
        int size =1;
        int index = foodType-1;
        if(foodList.get(index).getChosePrice().getDoubleSize() !=0)
            size = chooseSize(foodList, index);
        double price = size==1 ? foodList.get(index).getChosePrice().getSingleSize() : foodList.get(index).getChosePrice().getDoubleSize();
        // 출력
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-15s | W %.1f | %s%n"
                ,foodList.get(index).getFoodName()
                ,price
                , foodList.get(index).getExplain());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        System.out.println("-------------------------------------------------------");

        addtype = sc.nextLine();
        // 장바구니 추가시 유저의 장바구니에 데이터 입력
        if(addtype.equals("1"))
        {

            inputFoodData(foodList,index,price);
            System.out.println(foodList.get(index).getFoodName() + "가 장바구니에 추가되었습니다.");
            System.out.println();

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

    public void inputFoodData(ArrayList<FoodData> foodList ,int index, double price){
        String name = foodList.get(index).getFoodName();
        String explain = foodList.get(index).getExplain();
        if(!orderBasket.isEmpty())
        {
            boolean flag = false;
            for (int i = 0; i < orderBasket.size(); i++) {
                if(orderBasket.get(i).getFoodName().equals(foodList.get(index).getFoodName())
                        && orderBasket.get(i).getPrice() == price)
                {
                    orderBasket.get(index).setFoodCount(1);
                    flag =true;
                }
            }
            if(!flag)orderBasket.add(new FoodData(name,price,explain,1));
        }
        else orderBasket.add(new FoodData(name,price,explain,1));

    }

    // 사이즈 선택메뉴
    public int chooseSize(ArrayList<FoodData> foodData,int index){
        int chooseType =0;
        System.out.println("-------------------------------------------------------");

        System.out.printf("%-15s | W %.1f | %s%n"
                ,foodData.get(index).getFoodName()
                ,foodData.get(index).getChosePrice().getSingleSize()
                , foodData.get(index).getExplain());
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.printf("1. Single %.1f     2. Double %.1f %n"
                , foodData.get(index).getChosePrice().getSingleSize(),foodData.get(index).getChosePrice().getDoubleSize());
        System.out.println("-------------------------------------------------------");
        String type = sc.nextLine();
        if(type.equals("1"))
        {
            System.out.println("Single을 선택하셨습니다");
            chooseType =1;
        }
        else if (type.equals("2"))
        {
            System.out.println("Double을 선택하셨습니다");
            chooseType =2;
        }
        else
        {
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            chooseSize(foodData,index);
        }
        return chooseType;
    }
}
