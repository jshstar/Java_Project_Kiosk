package kiosk.domain;

public class ChosePrice {
    // 사이즈 선택 클래스
    private double singleSize; // single
    private double doubleSize; // double

    public ChosePrice(double singleSize,double doubleSize){
        this.singleSize = singleSize;
        this.doubleSize = doubleSize;
    }
    public double getSingleSize() {
        return singleSize;
    }

    public void setSingleSize(double singleSize) {
        this.singleSize = singleSize;
    }

    public double getDoubleSize() {
        return doubleSize;
    }

    public void setDoubleSize(double doubleSize) {
        this.doubleSize = doubleSize;
    }
}
