package ch.module.cardgame.card;

/**
 * Class which constitutes for a single slot where a card can be placed upon.
 */
public class CardField {

    private Card card;

    public CardField() {
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
