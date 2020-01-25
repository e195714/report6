package jp.ac.uryukyu.ie.e195714;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackMain {
    public static void main(String[] args){
        BlackJackMain blackJackMain = new BlackJackMain();
        BlackJack blackJack = new BlackJack();
        while (true){
            System.out.println("コマンドを入力してください");
            System.out.println("q・・・ゲーム終了");
            System.out.println("h・・・ヒット");
            System.out.println("s・・・スタンド");
            System.out.println("r・・・リスタート");
            blackJackMain.showStatus(blackJack);
            Scanner sc = new Scanner(System.in);
            String inputStr = sc.next();
            switch (inputStr){
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

    public void showStatus(BlackJack blackJack) {
        System.out.println("----------");
        ArrayList<Integer> dc = blackJack.getDealer().getCards();
        int dcc = blackJack.getDealer().getCardsCnt();
        System.out.println("ディーラーのスコア " + (blackJack.getGameEndFlag() ? blackJack.getScore(dc) : ""));
        String cardStr = "";
        if (blackJack.getGameEndFlag()) {
            for (int i = 0; i < dcc; i++) {
                if (i != 0)
                    cardStr += ",";
                cardStr += toDescription(dc.get(i));
            }
        } else {
            cardStr = toDescription(dc.get(0)) + ",もう一枚は秘密です";
        }
        System.out.println(cardStr);
        System.out.println("----------");
        // player
        ArrayList<Integer> pc = blackJack.getPlayer().getCards();
        int pcc = blackJack.getPlayer().getCardsCnt();
        System.out.println("あなたのスコア " + blackJack.getScore(pc));
        cardStr = "";
        for (int i = 0; i < pcc; i++) {
            if (i != 0)
                cardStr += ",";
            cardStr += toDescription(pc.get(i));
        }
        System.out.println(cardStr);
        System.out.println("----------");
        if (blackJack.getGameEndFlag()) {
            if (blackJack.gameJudgment() == 1) {
                System.out.println("おめでとう！あなたの勝利です！");
            } else if (blackJack.gameJudgment() == 0) {
                System.out.println("引き分けです");
            } else {
                System.out.println("ざんねん、あなたの負けです");
            }
            System.out.println("----------");
        }
    }

    private static String toDescription(int cardNumber) {
        String rank = toRank(toNumber(cardNumber));
        String suit = toSuit(cardNumber);

        return suit + "の" + rank;
    }

    private static String toRank(int number) {
        switch(number) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                String str = String.valueOf(number);
                return str;
        }
    }

    private static String toSuit(int cardNumber) {
        switch((cardNumber - 1)/13) {
            case 0:
                return "クラブ";
            case 1:
                return "ダイヤ";
            case 2:
                return "ハート";
            case 3:
                return "スペード";
            default:
                return "例外です";
        }
    }

    private static int toNumber(int cardNumber) {
        int number = cardNumber % 13;
        if(number == 0) {
            number = 13;
        }

        return number;
    }
}