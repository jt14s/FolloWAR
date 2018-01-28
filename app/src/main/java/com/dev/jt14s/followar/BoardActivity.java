package com.dev.jt14s.followar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Jorge on 1/27/2018.
 */

public class BoardActivity extends AppCompatActivity {

    private List<Card> playerDeck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiveIntentData();
    }

    private void receiveIntentData() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        playerDeck = (List<Card>) args.getSerializable("follower_cards");
    }
}
