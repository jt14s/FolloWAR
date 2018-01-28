package com.dev.jt14s.followar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 1/28/2018.
 */

public class PlayableArea implements PlayableAreaAction {

    private List<Card> activeCards = new ArrayList<>();

    @Override
    public void playPlayable(Playable card) {
        if (activeCards.size() <= 5) {
            activeCards.add((Card) card);
        }
    }
}
