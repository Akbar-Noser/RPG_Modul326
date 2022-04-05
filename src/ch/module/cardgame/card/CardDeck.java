package ch.module.cardgame.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> cards;
    private final int MAX_CARDS;

    public CardDeck() {
        this.MAX_CARDS = 30;
        this.cards = new ArrayList<>(MAX_CARDS);
    }

    private void generate() {
        for (int i = 0; i < MAX_CARDS; i++) {
            cards.add();
        }
    }
}
