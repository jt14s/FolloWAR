package com.dev.jt14s.followar;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Jorge on 1/28/2018.
 */

public abstract class CardArea {

    private CardData[] cardData;
    private int cardCount;

    public CardArea(int size) {
        cardData = new CardData[size];
        cardCount = 0;
        initArray();
    }

    private void initArray() {
        for (int i = 0; i < cardData.length; ++i)
            cardData[i] = null;
    }

    public boolean canHoldCard(int maxCount) {
        return cardCount <= maxCount;
    }

    public void addCard(CardData cardData, int maxCount) {
        if (canHoldCard(maxCount)) {
            for (int i = 0; i < maxCount; ++i) {
                if (this.cardData[i] == null) {
                    this.cardData[i] = cardData;
                    ++cardCount;
                }
            }
        }
    }

    public void removeCard(int cardPosition) {
        cardData[cardPosition] = null;
        --cardCount;
    }
}
