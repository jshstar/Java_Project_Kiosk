package kiosk;

public class FoodData extends Goods{
     public String foodname;
     public double[] chosePrice = new double[2];
     public double price;
     public String foodExplain;

     public int foodcount;

    public FoodData(String foodname, double[] price, String foodExplain) {
        this.foodname = foodname;
        this.chosePrice[0] = price[0];
        this.chosePrice[1] = price[1];
        this.foodExplain = foodExplain;
    }
    public FoodData(String foodname, double price, String foodExplain,int count){
        this.foodname= foodname;
        this.price = price;
        this.foodExplain = foodExplain;
        this.foodcount = count;
    }
    public String getName(){
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
    public double[] getChosePrice(){
        return this.chosePrice;
    }
    public double getPrice(){
        return this.price;
    }

    public String getExplain(){
        return this.foodExplain;
    }





}
