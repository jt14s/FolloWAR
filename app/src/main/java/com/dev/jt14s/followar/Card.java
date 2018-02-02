package com.dev.jt14s.followar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by Jorge on 1/31/2018.
 */

public class Card extends FrameLayout {

    private TextView attack;
    private TextView health;
    private TextView cost;
    private ImageView image;
    private String imageURL;

    public Card(Context context) {
        super(context);
        init(context);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Card(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.fragment_card, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        attack = (TextView) findViewById(R.id.attackPoints);
        health = (TextView) findViewById(R.id.healthPoints);
        cost = (TextView) findViewById(R.id.costPoints);
        image = (ImageView) findViewById(R.id.cardImage);
    }

    public void setHealth(int hitpoints) { health.setText(String.valueOf(hitpoints)); }

    public void setAttack(int damage) { attack.setText(String.valueOf(damage)); }

    public void setCost(int cost) { this.cost.setText(String.valueOf(cost)); }

    public void setPicture(String imageURL) { Picasso.with(getContext()).load(imageURL).into(image); }

    public int getAttack() { return Integer.parseInt(attack.getText().toString()); }

    public int getHealth() { return Integer.parseInt(health.getText().toString()); }

    public void setImageURL(String url) { imageURL = url; }

    public ImageView getImage() { return image; }

    public String getImageURL() { return imageURL; }

    public void setCardData(CardData data) {
        setAttack(data.getAttack());
        setCost(data.getCost());
        setHealth(data.getHealth());
        Picasso.with(getContext()).load(data.getCardImageURL()).into(image);
    }
}
