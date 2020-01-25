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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public boolean getGameEndFlag() {
        return gameEndFlag;
    }

    public void setGameEndFlag(boolean gameEndFlag) {
        this.gameEndFlag = gameEndFlag;
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
        this.player.setCardsCnt(0);
        this.dealer.setCards(new ArrayList<>());
        this.dealer.setCardsCnt(0);
        for(int i=0; i<2; i++){
            this.player.getCards().add(deck.get(deckDrowCount));
            deckDrowCount++;
            this.player.setCardsCnt(this.player.getCardsCnt() + 1);
            this.dealer.getCards().add(deck.get(deckDrowCount));
            this.dealer.setCardsCnt(this.dealer.getCardsCnt() + 1);
            deckDrowCount++;
        }
    }

    public void playerHit(){
        if(this.gameEndFlag == false) {
            this.player.getCards().add(deck.get(deckDrowCount));
            deckDrowCount++;
            this.player.setCardsCnt(this.player.getCardsCnt() + 1);
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
            int score = this.getScore(this.dealer.getCards());
            if (score < 17) {
                this.dealer.getCards().add(deck.get(deckDrowCount++));
                this.dealer.setCardsCnt(this.dealer.getCardsCnt() + 1);
            } else {
                this.dealerStand();
                break;
            }
        }
    }

    public void dealerStand(){
        this.gameEndFlag = true;
    }

    static int getScore(List<Integer> list) {
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

    public int gameJudgment() {
        int res = 0;
        int score1 = this.getScore(this.player.getCards());
        int score2 = this.getScore(this.dealer.getCards());
        int diff1 = 21 - score1;
        int diff2 = 21 - score2;
        if (22 <= score1 && 22 <= score2) {
            // *** プレイヤー・ディーラー共にバーストしているので負け *** //
            res = -1;
        } else if (22 <= score1 && score2 <= 21) {
            // *** プレイヤーバーストしているので負け *** //
            res = -1;
        } else if (score1 <= 21 && 22 <= score2) {
            // *** ディーラーバーストしているので勝ち *** //
            res = 1;
        } else {
            if (diff1 == diff2) {
                // *** 同スコアなら引き分け *** //
                res = 0;
                if (score1 == 21 && this.player.getCardsCnt() == 2 && this.dealer.getCardsCnt() != 2) {
                    // *** プレイヤーのみがピュアブラックジャックならプレイヤーの勝ち *** //
                    res = 1;
                }
            } else if (diff1 < diff2) {
                // *** プレイヤーの方が21に近いので勝ち *** //
                res = 1;
            } else {
                // *** ディーラーの方が21に近いので負け *** //
                res = -1;
            }
        }
        return res;
    }
}
