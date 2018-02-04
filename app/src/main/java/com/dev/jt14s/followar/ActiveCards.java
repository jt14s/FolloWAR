package com.dev.jt14s.followar;

import android.app.Activity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jorge on 2/2/2018.
 */

public class ActiveCards {

    @BindView(R.id.playerActiveCard1) Card activeCard1;
    @BindView(R.id.playerActiveCard2) Card activeCard2;
    @BindView(R.id.playerActiveCard3) Card activeCard3;

    private Card[] cards;
    private Card[] handles;
    private int activeCardCount;
    private Activity context;

    public ActiveCards(Activity context) {
        this.context = context;
        ButterKnife.bind(this, context);
        cards = new Card[] {null, null, null, null, null};
        handles = new Card[] {activeCard1, activeCard2, activeCard3};
        activeCardCount = 0;
    }

    public boolean canPlay() {
        return activeCardCount + 1 <= 3;
    }

    public void addCard(Card cardBeingPlayed) {
        for (int i = 0; i < 3; ++i) {
            if (cards[i] == null) {
                cards[i] = handles[i];
                cards[i].copyCardData(cardBeingPlayed);
                cards[i].setVisibility(View.VISIBLE);
                activeCardCount++;
                break;
            }
        }
    }

}
