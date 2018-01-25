package com.dev.jt14s.followar;

import android.widget.ImageView;

public class Card implements Playable {

    private int attack;
    private int health;
    //private ImageView cardImage;

    public Card(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    int getHealth() {
        return health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void attack(Card opponent) {
        opponent.setHealth(opponent.getHealth() - this.attack);
    }
}
