package com.dev.jt14s.followar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jorge on 1/27/2018.
 */

public class BoardActivity extends AppCompatActivity {

    @BindView(R.id.playerDeck) ImageButton uiPlayerDeck;
    @BindView(R.id.playerHand1) Card handCard1;
    @BindView(R.id.playerHand2) Card handCard2;
    @BindView(R.id.playerHand3) Card handCard3;
    @BindView(R.id.playerHand4) Card handCard4;
    @BindView(R.id.playerHand5) Card handCard5;
    
    private Deck deck;
    private Card[] hand;
    private int handCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.bind(this);

        deck = new Deck(receiveCards());
        hand = new Card[] {null, null, null, null, null};
        handCount = 0;
    }
    
    private ArrayList<CardData> receiveCards() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        return (ArrayList<CardData>) args.getSerializable("follower_cards");
    }
    
    @OnClick(R.id.playerDeck)
    public void deckClickedAction() {

        if (deck.canDraw()) {
            if (handCount + 1 <= 5) {
                for (int i = 0; i < 5; ++i) {
                    if (hand[i] == null) {
                        switch (i) {
                            case 0:
                                hand[i] = handCard1;
                                break;
                            case 1:
                                hand[i] = handCard2;
                                break;
                            case 2:
                                hand[i] = handCard3;
                                break;
                            case 3:
                                hand[i] = handCard4;
                                break;
                            case 4:
                                hand[i] = handCard5;
                                break;
                        }

                        hand[i].setVisibility(View.VISIBLE);
                        hand[i].setCardData(deck.drawCard());
                        handCount++;
                        break;
                    }
                }
            } else
                Toast.makeText(this, "Can't hold any more cards", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "You are out of cards to draw", Toast.LENGTH_SHORT).show();
    }

}
