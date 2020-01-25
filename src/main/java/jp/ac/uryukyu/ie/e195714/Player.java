package jp.ac.uryukyu.ie.e195714;

import java.util.ArrayList;

public class Player {
    private ArrayList<Integer> cards;

    public Player(){
        this.cards = new ArrayList<>();
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
    }
}
