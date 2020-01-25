package jp.ac.uryukyu.ie.e195714;

import java.util.Scanner;

public class BlackJackMain {
    public static void main(String[] args){
        BlackJack blackJack = new BlackJack();
        while (true){
            System.out.println("コマンドを入力してください");
            System.out.println("h・・・ヒット");
            System.out.println("s・・・スタンド");
            System.out.println("r・・・リスタート");
            Scanner sc = new Scanner(System.in);
            String inputStr = sc.next();
            switch (inputStr){
                case "h":
                    blackJack.playerHit();
                    break;
                case "s":
                    blackJack.playerStand();
                    break;
                case "r":
                    blackJack.gameInit();
                default:
                    System.out.println("”h”か”s”か”r”を入力してください");
                    break;
            }
        }
    }
}
