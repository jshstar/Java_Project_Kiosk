package kiosk.domain;


public class FoodData{
     // 음식 데이터 및 유저의 장바구니 클래스
     public String foodname; // 음식명
     public ChosePrice chosePrice; // 가격 Single  Double
     public double price; // 주문할 음식의 가격
     public String foodExplain;// 음식 설명
     public int foodcount; // 주문한 음식 개수

     // 음식 메뉴 Data 생성자
    public FoodData(String foodname, ChosePrice price, String foodExplain) {
        this.foodname = foodname;
        this.chosePrice = price;
        this.foodExplain = foodExplain;
    }
    // 유저 장바구니 Data 생성자
    public FoodData(String foodname, double price, String foodExplain,int count){
        this.foodname= foodname;
        this.price = price;
        this.foodExplain = foodExplain;
        this.foodcount = count;
    }
    public String getFoodName(){
        return this.foodname;
    }
    public int getFoodCount(){
        return this.foodcount;
    }
    public void setFoodCount(int num)
    {
        this.foodcount += num;
    }
    //public void init
    public ChosePrice getChosePrice(){
        return this.chosePrice;
    }
    public double getPrice(){
        return this.price;
    }
    public String getExplain(){
        return this.foodExplain;
    }





}
