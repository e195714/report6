/**
 * @file BlackJack.java
 * @brief ブラックジャッククラス
 * @author Keigo Nakada
 * @data 2020.01.20
 */
package jp.ac.uryukyu.ie.e195714;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class Blackjack
 * @brief ブラックジャック
 *
 */
public class BlackJack {
    private List<Integer> deck;
    private boolean gameEndFlag;
    private Player player;
    private Player dealer;
    private int deckDrowCount;

    /**
     * @brief コンストラクタ
     * @fn public BlackJack
     * @return なし
     *
     */
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

    /**
     * ゲームを初期化する。
     * 山札を用意し、シャッフルする。ディーラーとプレイヤーの手札を初期化し、それぞれにカードを２枚ずつ配る。
     *
     * @return なし
     */
    public void gameInit() {
        System.out.println("ゲームを開始します");
        this.gameEndFlag = false;
        this.deck = new ArrayList<>(52);

        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

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

    /**
     * プレイヤーがヒットする。
     *
     * @return なし
     */
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

    /**
     * プレイヤーがスタンドする。
     *
     * @return なし
     */
    public void playerStand(){
        this.dealerHit();
    }

    /**
     * ディーラーがヒットする。
     *
     * @return なし
     */
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

    /**
     * ディーラーがスタンドする。
     *
     * @return なし
     */
    public void dealerStand(){
        this.gameEndFlag = true;
    }

    /**
     * スコアを算出する。
     *
     * @param list　プレイヤーの手札
     * @return 足した結果
     */
    static int getScore(List<Integer> list) {
        int sum = 0;

        for(int i =0;i < list.size();i++) {
            sum = sum + toPoint(toNumber(list.get(i)));
        }

        return sum;
    }

    /**
     * J, Q, Kのスコアを10に変換する。
     *
     * @param num　カード
     * @return 変換した結果
     */
    private static int toPoint(int num) {
        if(num ==11||num == 12||num == 13) {
            num = 10;
        }

        return num;
    }

    /**
     * トランプの順番を数字に変換する。
     *
     * @param cardNumber　カードの順番
     * @return 変換した結果
     */
    static int toNumber(int cardNumber) {
        int number = cardNumber % 13;
        if(number == 0) {
            number = 13;
        }

        return number;
    }

    /**
     * 勝敗を判断する。
     *
     * @return 数字
     */
    public int gameJudgment() {
        int res = 0;
        int score1 = this.getScore(this.player.getCards());
        int score2 = this.getScore(this.dealer.getCards());
        int diff1 = 21 - score1;
        int diff2 = 21 - score2;
        if (22 <= score1 && 22 <= score2) {
            res = -1;
        } else if (22 <= score1 && score2 <= 21) {
            res = -1;
        } else if (score1 <= 21 && 22 <= score2) {
            res = 1;
        } else {
            if (diff1 == diff2) {
                res = 0;
                if (score1 == 21 && this.player.getCardsCnt() == 2 && this.dealer.getCardsCnt() != 2) {
                    res = 1;
                }
            } else if (diff1 < diff2) {
                res = 1;
            } else {
                res = -1;
            }
        }
        return res;
    }

    /**
     * プレーヤーたちのスコアと手札を表示する。
     *
     * @param blackJack　インスタンス
     */
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

    /**
     * カードの順番からトランプのスートと数字に変換する。
     *
     * @param cardNumber　カードの順番
     * @return 文字列
     */
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

    /**
     * カードの順番をトランプのスートに変換する。
     *
     * @param cardNumber　カードの順番
     * @return　文字列
     */
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
}

