package kiosk.domain;



// 유저가 구매한 주문 목록 저장 클래스
public class SellOrders {

    private String orderFood; // 음식정보
    private double orderPrice; // 음식가격

    // 주문 목록 생성자
    public SellOrders(String orderFood, double orderPirce) {
        this.orderFood = orderFood;
        this.orderPrice = orderPirce;
    }

    public String getOrderFood() {
        return orderFood;
    }
    public void setOrderFood(String orderFood) {
        this.orderFood = orderFood;
    }
    public double getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPirce(double orderPirce) {
        this.orderPrice = orderPirce;
    }
}
