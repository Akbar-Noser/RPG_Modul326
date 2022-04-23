package ch.module.cardgame.player;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardGenerator;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

    private static final int MAX_CARDS = 5;

    private List<Card> cards;

    public PlayerHand() {
        this.cards = new ArrayList<>(MAX_CARDS);
        for (int i = 0; i < 3; i++) {
            this.cards.add(CardGenerator.getInstance().generateRandomCard());
        }
    }

    public void addToHand(Card card) {
        if (cards.size() < MAX_CARDS)
            cards.add(card);
    }

    public void removeCardFromHand(Card card) {
        cards.remove(card);
    }

    public int amountOfCardsInHand() {
        return cards.size();
    }

    public static int getMAX_CARDS() {
        return MAX_CARDS;
    }

    public List<Card> getCards() {
        return cards;
    }
}
