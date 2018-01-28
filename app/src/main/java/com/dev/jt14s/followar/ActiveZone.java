package com.dev.jt14s.followar;

/**
 * Created by Jorge on 1/28/2018.
 */

public class ActiveZone extends CardArea {

    public ActiveZone() {
        super(3);
    }

    public void addCard(Card card) {
        super.addCard(card, 3);
    }

    public void removeCard(int cardPosition) {
        super.removeCard(cardPosition);
    }
}
