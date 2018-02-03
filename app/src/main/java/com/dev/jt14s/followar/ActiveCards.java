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

    @BindView(R.id.opponentActiveCard1) Card opponentCard1;
    @BindView(R.id.opponentActiveCard2) Card opponentCard2;
    @BindView(R.id.opponentActiveCard3) Card opponentCard3;

    private Card[] cards;
    private Card[] handles;
    private Card[] opponentHandles;
    private int activeCardCount;
    private Activity context;

    public ActiveCards(Activity context) {
        this.context = context;
        ButterKnife.bind(this, context);
        cards = new Card[] {null, null, null, null, null};
        handles = new Card[] {activeCard1, activeCard2, activeCard3};
        opponentHandles = new Card[] {opponentCard1, opponentCard2, opponentCard3};
        activeCardCount = 0;
    }

    public boolean canPlay() {
        return activeCardCount + 1 <= 5;
    }

    public void addCard(Card cardBeingPlayed) {
        for (int i = 0; i < 5; ++i) {
            if (cards[i] == null) {
                cards[i] = handles[i];
                cards[i].setVisibility(View.VISIBLE);
                cards[i].copyCardData(cardBeingPlayed);

                break;
            }
        }
    }

}
