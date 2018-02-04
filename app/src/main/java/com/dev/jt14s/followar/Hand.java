package com.dev.jt14s.followar;

import android.app.Activity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jorge on 2/2/2018.
 */

public class Hand {

    @BindView(R.id.playerHand1)
    Card handCard1;
    @BindView(R.id.playerHand2)
    Card handCard2;
    @BindView(R.id.playerHand3)
    Card handCard3;
    @BindView(R.id.playerHand4)
    Card handCard4;
    @BindView(R.id.playerHand5)
    Card handCard5;

    private Card[] hand;
    private Card[] handles;
    private int handSize;

    public Hand(Activity context) {
        ButterKnife.bind(this, context);
        hand = new Card[]{null, null, null, null, null};
        handles = new Card[]{handCard1, handCard2, handCard3, handCard4, handCard5};
        handSize = 0;
    }

    public boolean canHold() {
        return handSize + 1 <= 5;
    }

    public void addCard(CardData data) {
        for (int i = 0; i < 5; ++i) {
            if (hand[i] == null) {
                hand[i] = handles[i];
                hand[i].setVisibility(View.VISIBLE);
                hand[i].setCardData(data);
                handSize++;
                break;
            }
        }
    }

    public Card playCard(int index) {
        Card cardBeingPlayed = handles[index];
        hand[index] = null;
        handSize--;
        return cardBeingPlayed;
    }

}
