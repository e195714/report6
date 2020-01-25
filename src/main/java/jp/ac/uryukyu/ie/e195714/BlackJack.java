package jp.ac.uryukyu.ie.e195714;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJack {
    private List<Integer> deck;
    private boolean gameEndFlag;
    private Player player;
    private Player dealer;
    private int deckDrowCount;

    public BlackJack() {
        this.player = new Player();
        this.dealer = new Player();
        this.deckDrowCount = 0;
        this.gameInit();
    }

    public void gameInit() {
        System.out.println("ゲームを開始します");
        this.gameEndFlag = false;
        this.deck = new ArrayList<>(52);

        // リストに1-52の連番を代入
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        //山札をシャッフル
        Collections.shuffle(deck);

        this.player.setCards(new ArrayList<>());
        this.dealer.setCards(new ArrayList<>());
        for(int i=0; i<2; i++){
            this.player.getCards().add(deckDrowCount);
            deckDrowCount++;
            this.dealer.getCards().add(deckDrowCount);
            deckDrowCount++;
        }
    }

    public void playerHit(){
        if(this.gameEndFlag == false) {
            this.player.getCards().add(deckDrowCount);
            deckDrowCount++;
            int score = this.getScore(this.player.getCards());
            if(22 <= score)
                this.playerStand();
        }
    }

    public void playerStand(){
        this.dealerHit();
    }

    public void dealerHit(){
        for (;;) {
            int score = this.getScore(this.player.getCards());
            if (score < 17) {
                this.dealer.getCards().add(deckDrowCount);
                deckDrowCount++;
            } else {
                this.dealerStand();
                break;
            }
        }
    }

    public void dealerStand(){
        this.gameEndFlag = true;
    }

    private static int getScore(List<Integer> list) {
        int sum = 0;

        for(int i =0;i < list.size();i++) {
            sum = sum + toPoint(toNumber(list.get(i)));
        }

        return sum;
    }

    private static int toPoint(int num) {
        if(num ==11||num == 12||num == 13) {
            num = 10;
        }

        return num;
    }

    private static int toNumber(int cardNumber) {
        int number = cardNumber % 13;
        if(number == 0) {
            number = 13;
        }

        return number;
    }
}