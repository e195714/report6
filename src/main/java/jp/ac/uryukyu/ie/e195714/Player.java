package jp.ac.uryukyu.ie.e195714;

import java.util.ArrayList;

public class Player {
    private ArrayList<Integer> cards;
    private int cardsCnt;
    private int score;

    public Player(){
        this.cards = new ArrayList<>();
        this.cardsCnt = 0;
        this.score = 0;
    }

    public ArrayList<Integer> getCards() { return cards; }

    public void setCards(ArrayList<Integer> cards) { this.cards = cards; }

    public int getCardsCnt() {
        return cardsCnt;
    }

    public void setCardsCnt(int cardsCnt) {
        this.cardsCnt = cardsCnt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
