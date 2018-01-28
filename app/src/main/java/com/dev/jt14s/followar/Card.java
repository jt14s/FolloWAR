package com.dev.jt14s.followar;

import android.widget.ImageView;

import java.io.Serializable;

public class Card implements Playable, Serializable {

    private int attack;
    private int health;
    private int cost;
    //private ImageView cardImage;

    public int getAttack() {
        return attack;
    }

    int getHealth() {
        return health;
    }

    public int getCost() {
        return cost;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    void setHealth(int health) {
        this.health = health;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void attack(Card opponent) {
        opponent.setHealth(opponent.getHealth() - this.attack);
    }
}
