package com.dev.jt14s.followar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jorge on 1/27/2018.
 */

public class BoardActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    @BindView(R.id.playerHand1) Card handCard1;
    @BindView(R.id.playerHand2) Card handCard2;
    @BindView(R.id.playerHand3) Card handCard3;
    @BindView(R.id.playerHand4) Card handCard4;
    @BindView(R.id.playerHand5) Card handCard5;

    @BindView(R.id.playerActiveCard1) Card activeCard1;
    @BindView(R.id.playerActiveCard2) Card activeCard2;
    @BindView(R.id.playerActiveCard3) Card activeCard3;

    @BindView(R.id.playerPortrait) ImageView playerPortrait;
    @BindView(R.id.playerHPText) TextView playerHP;

    @BindView(R.id.opponentActiveCard1) Card opponentCard1;
    @BindView(R.id.opponentActiveCard2) Card opponentCard2;
    @BindView(R.id.opponentActiveCard3) Card opponentCard3;
    
    private Deck deck;
    private Hand hand;
    private ActiveCards activeCards;

    private Card[] playerActiveHandles;
    private Card[] playerHandHandles;
    private Card[] opponentActiveHandles;

    private int cardBeingHeld;
    private boolean validDrop;

    private enum CARD_TYPE {HAND, ACTIVE}
    private CARD_TYPE heldCardType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.bind(this);

        activeCards = new ActiveCards(this);
        deck = new Deck(receiveCards());
        hand = new Hand(this);

        playerHandHandles = new Card[] {handCard1, handCard2, handCard3, handCard4, handCard5};
        playerActiveHandles = new Card[] {activeCard1, activeCard2, activeCard3};
        opponentActiveHandles = new Card[] {opponentCard1, opponentCard2, opponentCard3};

        for (Card card : playerHandHandles)
            card.setOnTouchListener(this);
        for (Card card : playerActiveHandles)
            card.setOnTouchListener(this);
        for (Card card : opponentActiveHandles)
            card.setOnDragListener(this);
        findViewById(R.id.playerActiveCardArea).setOnDragListener(this);

        playerHP.setText(String.valueOf(20));
    }
    
    private ArrayList<CardData> receiveCards() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        return (ArrayList<CardData>) args.getSerializable("follower_cards");
    }

    @OnClick(R.id.playerDeck)
    public void deckClickedAction() {
        addCardToHand();
    }

    private void addCardToHand() {
        if (deck.canDraw()) {
            if (hand.canHold()) {
                hand.addCard(deck.drawCard());
            } else
                Toast.makeText(this, "Can't hold any more cards", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "You are out of cards to draw", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            cardBeingHeld = -1;
            validDrop = false;

            boolean found = false;
            for (int i = 0; i < 3; ++i) {
                if (view.getId() == playerActiveHandles[i].getId()) {
                    cardBeingHeld = i;
                    heldCardType = CARD_TYPE.ACTIVE;
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int i = 0; i < 5; ++i) {
                    if (view.getId() == playerHandHandles[i].getId()) {
                        cardBeingHeld = i;
                        heldCardType = CARD_TYPE.HAND;
                        break;
                    }
                }
            }

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
            if (view.getId() == R.id.playerActiveCardArea && heldCardType == CARD_TYPE.HAND) {
                if (activeCards.canPlay())
                    activeCards.addCard(hand.playCard(cardBeingHeld));
                else
                    Toast.makeText(this, "Active zone is full", Toast.LENGTH_SHORT).show();
            } else if (heldCardType == CARD_TYPE.ACTIVE) {
                int cardToAttack = -1;
                for (int i = 0; i < 3; ++i) {
                    if (view.getId() == opponentActiveHandles[i].getId()) {
                        cardToAttack = i;
                        break;
                    }
                }
                System.out.println("Attacking card " + (cardToAttack + 1));
                resetCardPosition(dragEvent);
            } else
                resetCardPosition(dragEvent);
        } else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED) {
            if (!validDrop)
                resetCardPosition(dragEvent);
        }

        return true;
    }

    private void resetCardPosition(DragEvent dragEvent) {
        final View droppedView = (View) dragEvent.getLocalState();
        droppedView.post(new Runnable(){
            @Override
            public void run() {
                droppedView.setVisibility(View.VISIBLE);
            }
        });
    }

}
