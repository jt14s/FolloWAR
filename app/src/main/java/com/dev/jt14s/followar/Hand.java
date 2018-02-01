package com.dev.jt14s.followar;

import android.content.Context;
import android.view.View;

/**
 * Created by Jorge on 1/28/2018.
 */

public class Hand extends CardArea {

    public Hand(Context context) { super(5); }

    public void addCard(CardData cardData) {
        super.addCard(cardData, 5);
    }

    public void playCard(int cardPosition) {
        super.removeCard(cardPosition);
    }
}
