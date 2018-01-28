package com.dev.jt14s.followar;

/**
 * Created by Jorge on 1/28/2018.
 */

public class Hand extends CardArea {

    public Hand() {
        super(5);
    }

    public void addCard(Card card) {
        super.addCard(card, 5);
    }

    public void playCard(int cardPosition) {
        super.removeCard(cardPosition);
    }
}
