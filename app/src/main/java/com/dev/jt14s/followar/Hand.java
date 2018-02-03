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

public class Hand implements View.OnTouchListener, View.OnDragListener {

    @BindView(R.id.playerHand1) Card handCard1;
    @BindView(R.id.playerHand2) Card handCard2;
    @BindView(R.id.playerHand3) Card handCard3;
    @BindView(R.id.playerHand4) Card handCard4;
    @BindView(R.id.playerHand5) Card handCard5;

    private Card[] hand;
    private Card[] handles;
    private int handSize;
    private Activity context;
    private ActiveCards activeCards;

    private int cardBeingHeld;
    private boolean validDrop;

    public Hand(Activity context, ActiveCards activeCards) {
        this.context = context;;
        ButterKnife.bind(this, context);
        hand = new Card[] {null, null, null, null, null};
        handles = new Card[] {handCard1, handCard2, handCard3, handCard4, handCard5};
        handSize = 0;
        this.activeCards = activeCards;

        for (Card card : handles)
            card.setOnTouchListener(this);
        context.findViewById(R.id.playerActiveCardArea).setOnDragListener(this);
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        System.out.println("In hands ontouch method");
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            cardBeingHeld = -1;
            validDrop = false;

            for (int i = 0; i < 5; ++i)
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
            if (!validDrop) {
                hand[cardBeingHeld].setVisibility(View.VISIBLE);
                return false;
            } else if (view.getId() == R.id.playerActiveCardArea){
                if (activeCards.canPlay()) {
                    activeCards.playCard(hand[cardBeingHeld]);
                    hand[cardBeingHeld] = null;
                    handSize--;
                }
            }
        } else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED) {
            if (!validDrop)
                hand[cardBeingHeld].setVisibility(View.VISIBLE);
        }

        return true;
    }
}
