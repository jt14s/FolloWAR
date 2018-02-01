package com.dev.jt14s.followar;

import java.io.Serializable;

public class CardData implements Serializable {

    private int attack;
    private int health;
    private int cost;
    private String cardImageURL;

    public int getAttack() {
        return attack;
    }

    int getHealth() { return health; }

    public int getCost() {
        return cost;
    }

    public String getCardImageURL() { return cardImageURL; }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    void setHealth(int health) {
        this.health = health;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCardImageURL(String cardImageURL) { this.cardImageURL = cardImageURL; }

}
