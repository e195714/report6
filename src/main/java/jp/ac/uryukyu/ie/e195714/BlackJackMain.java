/**
 * @file BlackJackMain.java
 * @brief ブラックジャックメインクラス
 * @author Keigo Nakada
 * @data 2020.01.20
 */
package jp.ac.uryukyu.ie.e195714;

import java.util.Scanner;

public class BlackJackMain {
    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        while (true) {
            System.out.println("コマンドを入力してください");
            System.out.println("q・・・ゲーム終了");
            System.out.println("h・・・ヒット");
            System.out.println("s・・・スタンド");
            System.out.println("r・・・リスタート");
            blackJack.showStatus(blackJack);
            Scanner sc = new Scanner(System.in);
            String inputStr = sc.next();
            switch (inputStr) {
                case "q":
                    System.out.println("ゲームを終了しました");
                    sc.close();
                    System.exit(0);
                    break;
                case "h":
                    blackJack.playerHit();
                    break;
                case "s":
                    blackJack.playerStand();
                    break;
                case "r":
                    blackJack.gameInit();
                    break;
                default:
                    System.out.println("”q”,”h”,”s”,”r”を入力してください");
                    break;
            }
        }
    }
}