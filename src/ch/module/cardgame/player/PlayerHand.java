package ch.module.cardgame.player;

import ch.module.cardgame.card.Card;
import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

    private final int MAX_CARDS = 5;

    private List<Card> cards;

    public PlayerHand() {
        cards = new ArrayList<>();
        //TODO call card generator for starting cards => like 3 for now
    }

    public void drawCard() {
        //TODO add draw from card deck
    }

    public List<Card> getCards() {
        return cards;
    }
}
