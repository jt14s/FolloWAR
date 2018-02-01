package com.dev.jt14s.followar;

import android.content.Context;

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
    private Context context;

    public Deck(Context context, List<CardData> cardsData) {
        this.context = context;
        this.cards = new ArrayList<>(generateCards(cardsData));
        deckCount = cardsData.size() > 20 ? MAX_CARDS : cardsData.size();
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

    private List<Card> generateCards(List<CardData> cardData) {
        List<Card> generatedCards = new ArrayList<>();
        for (CardData data : cardData) {

        }

        return generatedCards;
    }

}
