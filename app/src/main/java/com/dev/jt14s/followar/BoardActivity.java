package com.dev.jt14s.followar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 1/27/2018.
 */

public class BoardActivity extends AppCompatActivity {

    private PlayableSection playerSection, opponentSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPlayerSection();
    }

    private void initPlayerSection() {
        playerSection = new PlayableSection(receiveCards());
    }

    private ArrayList<Card> receiveCards() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        return (ArrayList<Card>) args.getSerializable("follower_cards");
    }
}
