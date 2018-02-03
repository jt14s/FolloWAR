package com.dev.jt14s.followar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jorge on 1/27/2018.
 */

public class BoardActivity extends AppCompatActivity {

    /*@BindView(R.id.playerHand1) Card handCard1;
    @BindView(R.id.playerHand2) Card handCard2;
    @BindView(R.id.playerHand3) Card handCard3;
    @BindView(R.id.playerHand4) Card handCard4;
    @BindView(R.id.playerHand5) Card handCard5;*/
    
    private Deck deck;
    private Hand hand;
    private ActiveCards activeCards;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.bind(this);

        activeCards = new ActiveCards(this);
        deck = new Deck(receiveCards());
        hand = new Hand(this, activeCards);

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

}
