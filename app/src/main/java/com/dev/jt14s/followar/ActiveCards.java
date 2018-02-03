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

    private int cardBeingHeld;
    private boolean validDrop;

    public ActiveCards(Activity context) {
        this.context = context;
        ButterKnife.bind(this, context);
        cards = new Card[] {null, null, null, null, null};
        handles = new Card[] {activeCard1, activeCard2, activeCard3};
        opponentHandles = new Card[] {opponentCard1, opponentCard2, opponentCard3};
        activeCardCount = 0;

        /*for (Card card : handles)
            card.setOnTouchListener(this);

        for (Card card : opponentHandles)
            card.setOnDragListener(this);*/
    }

    public boolean canPlay() {
        return activeCardCount + 1 <= 5;
    }

    public void playCard(Card cardBeingPlayed) {
        for (int i = 0; i < 5; ++i) {
            if (cards[i] == null) {
                cards[i] = handles[i];
                cards[i].copyCardData(cardBeingPlayed);
                cards[i].setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    /*@Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            cardBeingHeld = -1;
            validDrop = false;

            for (int i = 0; i < 3; ++i)
                if (view.getId() == handles[i].getId())
                    cardBeingHeld = i;

            view.setVisibility(View.INVISIBLE);
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, dragShadowBuilder, view,0);

            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENTERED) {
            validDrop = true;
        } else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_EXITED) {
            validDrop = false;
        } else if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
            int dropAreaID = getDropAreaID(view);
            if (!validDrop) {
                cards[cardBeingHeld].setVisibility(View.VISIBLE);
                return false;
            } else {

            }
        } else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED) {
            if (!validDrop)
                cards[cardBeingHeld].setVisibility(View.VISIBLE);
        }

        return true;
    }

    private int getDropAreaID(View v) {
        for (int i = 0; i < 3; ++i)
            if (v.getId() == opponentHandles[i].getId())
                return i;
        return -1;
    }*/
}
