package kiosk.handler;

import kiosk.domain.FoodData;
import kiosk.domain.SellOrders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class Order extends Menu {
    private static int waitNum = 0; // 주문 대기 순서
    private ArrayList<SellOrders> sellOrders = new ArrayList<>(); // 총 주문금엑에 대한 출력정보를 담은 객체

    private double getTotalPrice() {
        return this.totalPrice;
    }

    private void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    private double totalPrice =0;

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
            totalPrice = calculateOrderPrice(orderFoods,totalPrice);
            System.out.println(ORDERTITLE[1]);
            System.out.println("W " + Math.round(totalPrice*100)/100.0); // double 형의 출력근사치 삭제를 위해 round 사용
            System.out.println();
            System.out.println("1. 주문        2. 메뉴판" );
            System.out.println("-------------------------------------------------------");
            type = sc.nextLine();
            // 주문완료시 수행
            if(type.equals("1"))
                orderComplete(orderFoods, totalPrice);
            else if (type.equals("2")) {
                System.out.println("메뉴로 돌아갑니다.");
            }
            else{
                System.out.println("잘못입력 하셨습니다. 다시 입력해주세요");
                orders(orderFoods);
            }
        }
        else
            System.out.println("장바구니가 비어있네요 메뉴로 돌아갑니다.");

    }
    public double calculateOrderPrice(ArrayList<FoodData> orderFoods, double totalPrice){
        for (FoodData foodInfo: orderFoods ) {
            System.out.printf("%-15s | W %.1f | %d개 | %s%n"
                    , foodInfo.getFoodName(),foodInfo.getPrice(), foodInfo.getFoodCount() ,foodInfo.getExplain());

            // 주문한 총액 연산
            if(foodInfo.getFoodCount()>1)
                totalPrice+=foodInfo.getPrice()*foodInfo.getFoodCount();
            else totalPrice += foodInfo.getPrice();
        }
        return totalPrice;
    }


    // 주문 완료 메뉴
    public void orderComplete(ArrayList<FoodData> orderFoods , double totalPrice){
        // 주문한 총액 연산
        saveOrderPrice(orderFoods, totalPrice);
        // 주문 출력
        System.out.println("-------------------------------------------------------");
        System.out.println("주문 완료되었습니다!");
        waitNum++;
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

    public void saveOrderPrice(ArrayList<FoodData> orderFoods,double totalPrice){
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
        //  double 형의 출력근사치 삭제를 위해 round 사용
        System.out.println("현재까지 총 판매된 금액은 [ W " + Math.round(getTotalPrice()*100)/100.0 + " ] 입니다");
        System.out.println();
        System.out.println("1. 돌아가기");
        System.out.println("-------------------------------------------------------");
        endbutton = sc.nextLine();
        if(endbutton.equals("1"))
            System.out.println("메인화면으로 돌아갑니다.");
        else
        {
            System.out.println("잘못입력하셨습니다.");
            totalOrderPrice();
        }
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
            else System.out.println("장바구니가 비어있습니다. 메인메뉴로 돌아갑니다.");
        }
        else if(type.equals("2"))
        {
            partialOrderCancle(orderFoods);
        }
        else if(type.equals("3"))
            System.out.println("취소하셨습니다. 메인메뉴로 돌아갑니다.");
        else System.out.println("잘못 입력하셨습니다. 메인메뉴로 돌아갑니다.");

    }

    // 부분 취소
    public void partialOrderCancle(ArrayList<FoodData> orderFoods){
        int foodIndex;// 취소하고 싶은 음식 번호
        String type; // 조건 선택
        String reEnter; // 중복 개수 음식 다시 선택
        int foodCoundNum=1; // 중복된 음식 개수 변경 변수
        int inputCount = orderFoods.size(); // 유저가 제외한 음식 선택 개수 변수
        ArrayList<Integer> cancelIndex = new ArrayList<>(); // 제회할 음식의 번호가 담긴 List
        HashMap<String, Integer> cancelCountIndexMap = new HashMap<>(); // 음식의 중복을 제거할 변수인 HashMap <이름,중복제거>
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
                if(!cancelIndex.contains(i)) // 주문 취소 목록 추가시 주문목록에서 해당 음식 표시 제외
                    System.out.printf("%d. %-15s | W %.1f | %d개 | %s%n"
                        ,i+1 ,orderFoods.get(i).getFoodName(),orderFoods.get(i).getPrice()
                        , orderFoods.get(i).getFoodCount() ,orderFoods.get(i).getExplain());
            }
            System.out.println("-------------------------------------------------------");
            // 취소 목록 출력
            System.out.println("-------------------------------------------------------");
            System.out.println("취소하실 음식을 입력해주세요");
            System.out.println("취소 목록");
            for (int i = 0; i < cancelIndex.size(); i++) {
                if(cancelCountIndexMap.containsKey(orderFoods.get(cancelIndex.get(i)).getFoodName()))
                {
                    System.out.printf("%d. %-15s | %d개 %n"
                            ,cancelIndex.get(i)+1 ,orderFoods.get(cancelIndex.get(i)).getFoodName()
                            ,cancelCountIndexMap.get(orderFoods.get(cancelIndex.get(i)).getFoodName()));
                }
                else{
                    System.out.printf("%d. %-15s | %d개 %n"
                            ,cancelIndex.get(i)+1 ,orderFoods.get(cancelIndex.get(i)).getFoodName()
                            , orderFoods.get(cancelIndex.get(i)).getFoodCount());
                }
            }
            System.out.println("취소완료: 0");
            System.out.println("-------------------------------------------------------");
            // 주문 목록 선택
            while (!sc.hasNextInt())
            {
                sc.next();
                System.out.println("문자를 입력하셨습니다. 다시 입력해주세요");
            }
            foodIndex = sc.nextInt()-1;
            sc.nextLine();
            // 취소기능에 대한 if문
            if(foodIndex>=0 && foodIndex<orderFoods.size() &&inputCount >0 && !cancelIndex.contains(foodIndex) )
            {
                if(orderFoods.get(foodIndex).getFoodCount() >1) // 선택한 음식의 개수가 2이상일 때
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
                            while (!sc.hasNextInt())
                            {
                                sc.next();
                                System.out.println("문자를 입력하셨습니다. 다시 입력해주세요");
                            }
                            foodCoundNum = sc.nextInt(); // 중복 차감할 개수 입력
                            sc.nextLine();
                            // 중복 차감시 0, 0보다 작을경우, 0보다 클경우 조건문
                            if(foodCoundNum==0)
                                break;
                            else if(orderFoods.get(foodIndex).getFoodCount() - foodCoundNum == 0)
                            {
                                System.out.println("전부 취소하셨습니다");
                                cancelIndex.add(foodIndex);
                                inputCount--;
                                break;
                            }
                            else if(orderFoods.get(foodIndex).getFoodCount() - foodCoundNum < 0)
                            {
                                System.out.println("수량을 초과해서 입력했습니다. 다시 입력해주세요");
                            }
                            else
                            {
                                System.out.println("-------------------------------------------------------");
                                System.out.println( foodCoundNum + "개 취소를 입력하셨습니다.");
                                System.out.println("입력 하시겠습니까?");
                                System.out.println("1. 입력        2.재입력");
                                System.out.println("-------------------------------------------------------");
                                reEnter = sc.nextLine(); // 타입 선택
                                if(reEnter.equals("1"))  //1. 입력시 데이터 저장
                                {

                                    cancelCountIndexMap.put(orderFoods.get(foodIndex).getFoodName(),foodCoundNum);
                                    cancelIndex.add(foodIndex);
                                    inputCount--;

                                }
                                else if(reEnter.equals("2")){ //2. 재입력시 데이터 재입력
                                    continue;
                                }
                                else System.out.println("잘못된 입력정보입니다. 주문 목록 화면으로 돌아갑니다.");
                                    break;
                            }
                        }
                    }
                    else if (type.equals("2")) { // 중복이 없을때
                        cancelIndex.add(foodIndex);
                        inputCount--;
                    }
                    else System.out.println("잘못된 입력 정보입니다 주문 목록 화면으로 돌아갑니다");
                }
                else // 모두 취소
                {
                    cancelIndex.add(foodIndex);
                    inputCount--;
                }
            }
            else if(cancelIndex.contains(foodIndex)) // 전에 취소한 음식이 있는지 확인
                System.out.println("이미 취소하신 음식입니다. 다시 선택해주세요.");
            else if(foodIndex>= orderFoods.size()) // 목록에 입력한 번호가 있는지 확인 없을시 재입력
                System.out.println("취소목록에 번호가 없습니다. 다시선택해주세요");
            else if(foodIndex == -1) // 종료
                break;
            else System.out.println("숫자정보를 입력해주세요"); // 예외처리

        }

        // 취소목록 Data 처리
        if(!cancelIndex.isEmpty())
        {
            // 취소목록 Data 출력
            System.out.println("-------------------------------------------------------");
            System.out.println("취소하실 음식 목록");
            for (int i = 0; i < cancelIndex.size(); i++) {
                if(cancelCountIndexMap.containsKey(orderFoods.get(cancelIndex.get(i)).getFoodName()))
                {
                    System.out.printf("%d. %-15s | W %.1f | %d개 %n"
                            ,cancelIndex.get(i)+1 ,orderFoods.get(cancelIndex.get(i)).getFoodName()
                            ,orderFoods.get(cancelIndex.get(i)).getPrice()
                            ,cancelCountIndexMap.get(orderFoods.get(cancelIndex.get(i)).getFoodName()));
                }
                else{
                    System.out.printf("%d. %-15s | W %.1f | %d개 %n"
                            ,cancelIndex.get(i)+1 ,orderFoods.get(cancelIndex.get(i)).getFoodName()
                            ,orderFoods.get(cancelIndex.get(i)).getPrice()
                            , orderFoods.get(cancelIndex.get(i)).getFoodCount());
                }

            }
            System.out.println("취소하시겠습니까?");
            System.out.println("1. 확인        2. 취소");
            System.out.println("-------------------------------------------------------");
            type = sc.nextLine();

            // 취소 데이터 장바구니에 입력
            if(type.equals("1"))
            {

                inputCancelFoodDate(cancelIndex,orderFoods,cancelCountIndexMap);
                // Data 변경후 저장했었던 데이터 클리어
                cancelIndex.clear();
                cancelCountIndexMap.clear();
                System.out.println("선택하신 상품이 취소되었습니다.");
            }
            // 취소시 저장했었던 데이터 클리어
            else if(type.equals("2"))
            {
                cancelIndex.clear();
                cancelCountIndexMap.clear();
                System.out.println("선택하신 상품이 취소되었습니다.");
            }
            // 잘못입력 예외처리
            else {
                System.out.println("잘못입력 하셨습니다 처음으로 돌아갑니다.");
                cancelIndex.clear();
                cancelCountIndexMap.clear();
                partialOrderCancle(orderFoods);
            }
        }
        else System.out.println("취소할 음식이 없음으로 메뉴로 돌아갑니다.");
    }

    // 장바구니에서 취소목록에 있는 데이터 개수 축소 및 데이터 제거
    public void inputCancelFoodDate(ArrayList<Integer> cancelIndex, ArrayList<FoodData> orderFoods
            , HashMap<String, Integer> cancelCountIndexMap){
        int count=0;
        Collections.sort(cancelIndex);
        for (int i = 0; i < cancelIndex.size(); i++) {
            String foodName = orderFoods.get(cancelIndex.get(i)-count).getFoodName();
            if(cancelCountIndexMap.containsKey(foodName))
            {
                orderFoods.get(cancelIndex.get(i)-count)
                        .setFoodCount(-cancelCountIndexMap.get(foodName));
            }
            else{
                orderFoods.remove(cancelIndex.get(i)-count);
                count++;
            }
        }
    }





}
