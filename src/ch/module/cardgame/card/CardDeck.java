package ch.module.cardgame.card;

import java.util.ArrayDeque;
import java.util.Deque;

public class CardDeck {
    private Deque<Card> cards;
    private final int MAX_CARDS;

    public CardDeck() {
        this.MAX_CARDS = 30;
        this.cards = new ArrayDeque<>(MAX_CARDS);
        generate();
    }

    /**
     * Generates the cards of the deck.
     */
    private void generate() {
        for (int i = 0; i < MAX_CARDS; i++) {
            cards.push(CardFactory.getInstance().generateRandomCard());
        }
    }

    public Card popTopCard() {
        return cards.pop();
    }

    public int getMAX_CARDS() {
        return MAX_CARDS;
    }

    public Deque<Card> getCards() {
        return cards;
    }
}
