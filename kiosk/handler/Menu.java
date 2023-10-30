package kiosk.handler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    //MENU Data
    protected final String[][] SHACKSHACKMENU = {{"Burgers","Forzen Custard","Drinks","Beer"},
                                        {"앵거스 비프 통살을 다져만든 버거","매장에서 신선하게 만드는 아이스크림"
                                        ,"매장에서 직접 만드는 음료","뉴욕 브루클린 브루어리에서 양조한 맥주"}};
    protected final String[][] ORDERMENU = {{"Order","Cancel"},{"장바구니를 확인 후 주문합니다.","진행중인 주문을 취소합니다."}};

    protected final String[] MENUTITLE = {"[ SHACKSHACK MENU ]","[ ORDER MENU ]"} ;
    protected final String[] ORDERTITLE = {"[ Orders ]", "[ Total ]"};
    protected final String[] FOODTITLE = {"[ Burgers MENU ]", "[ Forzen Custard MENU ]"
                                        , "[ Drink MENU ]", "[ Beer MENU ]"};
    private static int menuType; // menu 선택창 번호 입력
    Scanner sc = new Scanner(System.in);

    // 음식 종류 메뉴창
    public void mainMenu() {

        // 메뉴 출력
        int type;
        System.out.println("-------------------------------------------------------");
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println();
        System.out.println(MENUTITLE[0]);
        for (int i = 0; i < SHACKSHACKMENU[0].length + ORDERMENU[0].length; i++) {
            if (i < SHACKSHACKMENU[0].length)
                System.out.printf("%d. %-15s | %s%n", i + 1, SHACKSHACKMENU[0][i], SHACKSHACKMENU[1][i]);
            else {
                if (i == SHACKSHACKMENU[0].length) {
                    System.out.println();
                    System.out.println(MENUTITLE[1]);
                }
                System.out.printf("%d. %-10s | %s%n", i + 1, ORDERMENU[0][i - SHACKSHACKMENU[0].length], ORDERMENU[1][i - SHACKSHACKMENU[0].length]);
            }
        }
        System.out.println("-------------------------------------------------------");
        try{
            type = sc.nextInt();
            sc.nextLine();
            setMenuType(type);
        }catch (InputMismatchException e)
        {
            sc.nextLine();
            System.out.println("잘못된 입력정보입니다 숫자를 입력해주세요");
        }
    }

    public void setMenuType(int type){
        menuType = type;
    }
    public int getMenuType(){
        return menuType;
    }




}
