package ch.module.cardgame.graphics.mediator;

import ch.module.cardgame.graphics.deck.VisualDeck;
import ch.module.cardgame.graphics.hand.VisualHand;

public class DeckHandMediator {
    private VisualDeck deck;
    private VisualHand hand;

    public void rerender() {
        deck.rerender();
        hand.rerender();
    }

    public void setDeck(VisualDeck deck) {
        this.deck = deck;
    }

    public void setHand(VisualHand hand) {
        this.hand = hand;
    }
}
