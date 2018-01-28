package com.dev.jt14s.followar;

import java.util.ArrayList;

/**
 * Created by Jorge on 1/28/2018.
 */

class PlayableSection {

    private Deck deck;
    private Hand hand;
    private ActiveZone activeZone;

    public PlayableSection(ArrayList<Card> cards) {
        deck = new Deck(cards);
        hand = new Hand();
        activeZone = new ActiveZone();
    }
}
