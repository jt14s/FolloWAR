package com.dev.jt14s.followar;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Jorge on 1/28/2018.
 */

public class PlayableSection {

    private Deck deck;
    private Hand hand;
    private ActiveZone activeZone;

    public PlayableSection(Context context, ArrayList<CardData> cardData) {
        //activeZone = new ActiveZone(context);
        //deck = new Deck(context, cardData);
        hand = new Hand(context);
    }


}
