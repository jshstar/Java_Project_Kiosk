package kiosk;

public class SellOrders {

    String orderFood;
    double orderPirce;
    public SellOrders(String orderFood, double orderPirce) {
        this.orderFood = orderFood;
        this.orderPirce = orderPirce;
    }

    public String getOrderFood() {
        return orderFood;
    }

    public void setOrderFood(String orderFood) {
        this.orderFood = orderFood;
    }

    public double getOrderPirce() {
        return orderPirce;
    }

    public void setOrderPirce(double orderPirce) {
        this.orderPirce = orderPirce;
    }
}
