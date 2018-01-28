package com.dev.jt14s.followar;

/**
 * Created by Jorge on 1/28/2018.
 */

public abstract class CardArea {

    private Card[] cards;
    private int cardCount;

    public CardArea(int size) {
        cards = new Card[size];
        cardCount = 0;
        initArray();
    }

    private void initArray() {
        for (int i = 0; i < cards.length; ++i)
            cards[i] = null;
    }

    public boolean canHoldCard(int maxCount) {
        return cardCount <= maxCount;
    }

    public void addCard(Card card, int maxCount) {
        if (canHoldCard(maxCount)) {
            for (int i = 0; i < maxCount; ++i) {
                if (cards[i] == null) {
                    cards[i] = card;
                    ++cardCount;
                }
            }
        }
    }

    public void removeCard(int cardPosition) {
        cards[cardPosition] = null;
        --cardCount;
    }
}
