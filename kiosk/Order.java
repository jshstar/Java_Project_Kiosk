package kiosk;

import javax.swing.*;
import java.util.*;

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
                        , foodInfo.getFoodName(),foodInfo.getPrice(), foodInfo.getFoodCount() ,foodInfo.getExplain());

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
                    sellOrders.add(new SellOrders(foodInfo.getFoodName(),foodInfo.getPrice()));
                }
            }
            else sellOrders.add(new SellOrders(foodInfo.getFoodName(),foodInfo.getPrice()));
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
        System.out.println("1. 확인        2. 장바구니 부분 취소        3.취소");
        type = sc.nextLine();
        if(type.equals("1"))
        {
            System.out.println();
            System.out.println("진행하던 주문이 취소되었습니다.");
            if(!orderFoods.isEmpty())
                orderFoods.clear();
        }
        else if(type.equals("2"))
        {
            partialOrderCancle(orderFoods);
        }
    }

    // 부분 취소
    public void partialOrderCancle(ArrayList<FoodData> orderFoods){
        int foodIndex;// 취소하고 싶은 음식 번호
        String type; // 조건 선택
        String reEnter; // 중복 개수 음식 다시 선택
        int foodCoundNum=1; // 중복된 음식 개수 변경 변수
        int inputCount = orderFoods.size(); // 유저가 제외한 음식 선택 개수 변수
        ArrayList<Integer> cancleIndex = new ArrayList<>(); // 제회할 음식의 번호가 담긴 List
        HashMap<String, Integer> cancleCountIndexMap = new HashMap<>(); // 음식의 중복을 제거할 변수인 HashMap <이름,중복제거>
        while(true)
        {

            // 취소 목록이 없을시 출력
            // inputCount 초기값 = orderFood객체의 크기
            if(inputCount ==0)
            {
                System.out.println("취소하실 음식이 없습니다");
                break;
            }
            // 주문 목록 출력
            System.out.println("-------------------------------------------------------");
            System.out.println("주문 목록");
            for (int i = 0; i < orderFoods.size(); i++) {
                System.out.printf("%d. %-15s | W %.1f | %d개 | %s%n"
                        ,i+1 ,orderFoods.get(i).getFoodName(),orderFoods.get(i).getPrice()
                        , orderFoods.get(i).getFoodCount() ,orderFoods.get(i).getExplain());
            }
            System.out.println("-------------------------------------------------------");
            // 취소 목록 출력
            System.out.println("-------------------------------------------------------");
            System.out.println("취소하실 음식을 입력해주세요");
            System.out.println("취소 목록");
            for (int i = 0; i < cancleIndex.size(); i++) {
                if(cancleCountIndexMap.containsKey(orderFoods.get(cancleIndex.get(i)).getFoodName()))
                {
                    System.out.printf("%d. %-15s | %d개 %n"
                            ,cancleIndex.get(i)+1 ,orderFoods.get(cancleIndex.get(i)).getFoodName()
                            ,cancleCountIndexMap.get(orderFoods.get(cancleIndex.get(i)).getFoodName()));
                }
                else{
                    System.out.printf("%d. %-15s | %d개 %n"
                            ,cancleIndex.get(i)+1 ,orderFoods.get(cancleIndex.get(i)).getFoodName()
                            , orderFoods.get(cancleIndex.get(i)).getFoodCount());
                }
            }
            System.out.println("나가기: 0");
            System.out.println("-------------------------------------------------------");
            // 주문 목록 선택
            foodIndex = sc.nextInt()-1;
            sc.nextLine();

            // 취소기능에 대한 if문
            if(foodIndex>=0 && inputCount >0 && !cancleIndex.contains(foodIndex) )
            {
                if(orderFoods.get(foodIndex).getFoodCount() >1 && foodIndex<orderFoods.size() ) // 선택한 음식의 개수가 2이상일 때
                {
                    System.out.println("-------------------------------------------------------");
                    System.out.println("선택하신 음식의 개수가 " +
                            orderFoods.get(foodIndex).getFoodCount() + "개 입니다");
                    System.out.println("1. 부분 취소        2. 전부 취소");
                    System.out.println("-------------------------------------------------------");
                    type = sc.nextLine(); // 취소 타입입력
                    if(type.equals("1"))
                    {
                        System.out.println("-------------------------------------------------------");
                        System.out.println("부분 취소를 선택하셨습니다 몇개를 취소하시겠습니까?");
                        System.out.println("0: 돌아가기");
                        System.out.println("-------------------------------------------------------");
                        while (true){
                            System.out.println("개수를 입력해주세요.");
                            foodCoundNum = sc.nextInt();
                            sc.nextLine();
                            if(foodCoundNum==0)
                                break;
                            else if(orderFoods.get(foodIndex).getFoodCount() - foodCoundNum == 0)
                            {
                                System.out.println("전부 취소하셨습니다");
                                cancleIndex.add(foodIndex);
                                inputCount--;
                                break;
                            }
                            else if(orderFoods.get(foodIndex).getFoodCount() - foodCoundNum < 0)
                            {
                                System.out.println("수량을 초과해서 입력했습니다. 다시 입력해주세요");
                            }
                            else
                            {
                                System.out.println( foodCoundNum + "개 취소를 입력하셨습니다.");
                                System.out.println("다시 입력하시겠습니까?");
                                System.out.println("1.예         2.아니오");
                                reEnter = sc.nextLine();
                                if(reEnter.equals("1"))
                                {
                                    System.out.println("개수를 입력해주세요~");
                                    foodCoundNum = sc.nextInt();
                                    sc.nextLine();
                                    cancleCountIndexMap.put(orderFoods.get(foodIndex).getFoodName(),foodCoundNum);
                                    cancleIndex.add(foodIndex);
                                }
                                else{
                                    cancleCountIndexMap.put(orderFoods.get(foodIndex).getFoodName(),foodCoundNum);
                                    cancleIndex.add(foodIndex);
                                }
                                inputCount--;
                                break;
                            }
                        }
                    }
                    else if (type.equals("2")) { // 중복이 없을때
                        cancleIndex.add(foodIndex);
                        inputCount--;
                    }
                }
                else
                {
                    cancleIndex.add(foodIndex);
                    inputCount--;
                }
            }
            else if(cancleIndex.contains(foodIndex))
                System.out.println("이미 취소하신 음식입니다. 다시 선택해주세요.");
            else if(foodIndex>orderFoods.size()-1)
                System.out.println("취소목록에 번호가 없습니다. 다시선택해주세요");
            else if(foodIndex == -1)
                break;
        }

        if(!cancleIndex.isEmpty())
        {
            System.out.println("-------------------------------------------------------");
            System.out.println("취소하실 음식 목록");
            for (int i = 0; i < cancleIndex.size(); i++) {
                if(cancleCountIndexMap.containsKey(orderFoods.get(cancleIndex.get(i)).getFoodName()))
                {
                    System.out.printf("%d. %-15s | W %.1f | %d개 %n"
                            ,cancleIndex.get(i)+1 ,orderFoods.get(cancleIndex.get(i)).getFoodName()
                            ,orderFoods.get(cancleIndex.get(i)).getPrice()
                            ,cancleCountIndexMap.get(orderFoods.get(cancleIndex.get(i)).getFoodName()));
                }
                else{
                    System.out.printf("%d. %-15s | W %.1f | %d개 %n"
                            ,cancleIndex.get(i)+1 ,orderFoods.get(cancleIndex.get(i)).getFoodName()
                            ,orderFoods.get(cancleIndex.get(i)).getPrice()
                            , orderFoods.get(cancleIndex.get(i)).getFoodCount());
                }

            }
            System.out.println("취소하시겠습니까?");
            System.out.println("1. 확인        2. 취소");
            type = sc.nextLine();
            if(type.equals("1"))
            {
                int count=0;
                Collections.sort(cancleIndex);
                for (int i = 0; i < cancleIndex.size(); i++) {
                    String foodName = orderFoods.get(cancleIndex.get(i)-count).getFoodName();
                    if(cancleCountIndexMap.containsKey(foodName))
                    {
                        orderFoods.get(cancleIndex.get(i)-count)
                                .setFoodCount(-cancleCountIndexMap.get(foodName));
                    }
                    else{
                        orderFoods.remove(cancleIndex.get(i)-count);
                        count++;
                    }

                }
                cancleIndex.clear();
                cancleCountIndexMap.clear();
            }
            else
            {
                cancleIndex.clear();
                cancleCountIndexMap.clear();
            }
            System.out.println("-------------------------------------------------------");
        }
        else System.out.println("취소할 음식이 없음으로 메뉴로 돌아갑니다.");
    }






}
