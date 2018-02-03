package com.dev.jt14s.followar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jorge on 2/1/2018.
 */

public class Deck {

    private List<CardData> cards;
    private int topOfDeck;

    public Deck(ArrayList<CardData> data) {
        cards = data;
        topOfDeck = data.size() - 1;
        shuffleDeck();
    }

    public boolean canDraw() {
        return topOfDeck - 1 >= -1; //check to -1 due to n through 0 being valid topOfDeck
    }

    public CardData drawCard() {
        CardData cardData = cards.get(topOfDeck);
        topOfDeck--;
        return cardData;
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }
}
