package kiosk;

import java.util.ArrayList;

public class Order extends Menu{
    static int waitNum = 0; // 주문 대기 순서
    private ArrayList<SellOrders> sellOrders = new ArrayList<>();

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    double totalPrice =0;

    // 주문 메뉴
    public void orders(ArrayList<FoodData> orderFoods){
        String type;
        double totalPrice=0;
        if(!orderFoods.isEmpty())
        {
            System.out.println("-------------------------------------------------------");
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println(ORDERTITLE[0]);
            // 주문 목록 출력 및 가격 토탈값 계산
            for (FoodData foodInfo: orderFoods ) {
                System.out.printf("%-15s | W %.1f | %d개 | %s%n"
                        , foodInfo.getName(),foodInfo.getPrice(), foodInfo.getFoodCount() ,foodInfo.getExplain());

                // 주문한 총액 연산
                if(foodInfo.getFoodCount()>1)
                    totalPrice+=foodInfo.price*foodInfo.getFoodCount();

                else totalPrice += foodInfo.price;
            }

            System.out.println(ORDERTITLE[1]);
            System.out.println("W " + totalPrice);
            System.out.println();
            System.out.println("1. 주문        2. 메뉴판" );
            System.out.println("-------------------------------------------------------");
            type = sc.nextLine();
            // 주문완료시 수행
            if(type.equals("1"))
                orderComplete(orderFoods, totalPrice);
        }
        else
            System.out.println("장바구니가 비어있네요 메뉴로 돌아갑니다.");

    }

    // 주문 완료 메뉴
    public void orderComplete(ArrayList<FoodData> orderFoods , double totalPrice){
        // 주문한 총액 연산
        for (FoodData foodInfo: orderFoods ) {
            if(foodInfo.getFoodCount()>1)
            {
                for (int i = 0; i < foodInfo.getFoodCount(); i++) {
                    sellOrders.add(new SellOrders(foodInfo.getName(),foodInfo.getPrice()));
                }
            }
            else sellOrders.add(new SellOrders(foodInfo.getName(),foodInfo.getPrice()));
        }
        setTotalPrice(totalPrice);

        // 주문 출력
        System.out.println("-------------------------------------------------------");
        System.out.println("주문 완료되었습니다!");
        this.waitNum++;
        System.out.println("대기번호는 " + "[ " + waitNum + " ]" + "입니다.");
        System.out.println("3초후 메뉴판으로 돌아갑니다.");
        System.out.println("-------------------------------------------------------");
        orderFoods.clear();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    // 총 판매금액 현황 메뉴
    public void totalOrderPrice(){
        String endbutton;
        System.out.println("-------------------------------------------------------");
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        for (SellOrders sellData: sellOrders) {
            System.out.printf("- %-10s | W %.1f %n", sellData.getOrderFood(),sellData.getOrderPrice());
        }
        System.out.println();
        System.out.println("[ 총 판매금액 현황 ]");
        System.out.println("현재까지 총 판매된 금액은 [ W " + Math.round(getTotalPrice()*100)/100.0 + " ] 입니다");
        System.out.println();
        System.out.println("1. 돌아가기");
        System.out.println("-------------------------------------------------------");
        endbutton = sc.nextLine();
        if(endbutton.equals("1"))
            System.out.println("메인화면으로 돌아갑니다.");
    }


    // 주문 취소 메뉴
    public void orderCancle(ArrayList<FoodData> orderFoods){
        String type;
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        type = sc.nextLine();
        if(type.equals("1"))
        {
            System.out.println();
            System.out.println("진행하던 주문이 취소되었습니다.");
            if(!orderFoods.isEmpty())
                orderFoods.clear();
        }
    }




}
