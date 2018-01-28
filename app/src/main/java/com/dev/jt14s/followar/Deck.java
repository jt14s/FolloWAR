package com.dev.jt14s.followar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jorge on 1/28/2018.
 */

public class Deck {

    static final int MAX_CARDS = 20;
    private List<Card> cards;
    private int deckCount;

    public Deck(ArrayList<Card> followerCards) {
        this.cards = new ArrayList<>(followerCards);
        deckCount = followerCards.size() > 20 ? MAX_CARDS : followerCards.size();
    }

    public boolean canDrawCard() { return (deckCount - 1) > 0; }

    public Card drawCard() {
        if (canDrawCard()) {
            return cards.get(--deckCount);
        }
        return null;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}
