package ch.module.cardgame.player;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardDeck;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.mediator.PlayerPlayFieldMediator;

public abstract class Player {
    protected PlayerStats stats;
    protected PlayerHand hand;
    protected CardDeck deck;
    protected PlayerPlayFieldMediator playerPlayFieldMediator;

    protected Player() {
        this.stats = new PlayerStats();
        this.hand = new PlayerHand();
        this.deck = new CardDeck();
        PlayField.getInstance().registerPlayer(this);
    }

    public abstract void endTurn();

    /**
     * Adds card from the top of the deck to the hand if the hand doesn't hold the maximum capacity
     */
    public void drawCard () {
        if(hand.amountOfCardsInHand() < hand.getMAX_CARDS())
            hand.addToHand(deck.popTopCard());
    }

    /**
     * The player plays a card
     *
     * @param card The card to be played
     * @param index The index of the field on which the card should be played
     * @return true if the card was successfully played, false otherwise
     */
    public boolean playCard(Card card, int index) {
        boolean successful = playerPlayFieldMediator.playCard(this, card, index);
        if (successful)
            stats.decrementEnergyBy(card.getSummonEnergyPoints());
        return successful;
    }

    /**
     * Removes a cards from the players hand
     *
     * @param cardToRemove the card to be removed
     */
    public void removeCardFromHand(Card cardToRemove) {
        hand.removeCardFromHand(cardToRemove);
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setPlayerPlayFieldMediator(PlayerPlayFieldMediator playerPlayFieldMediator) {
        this.playerPlayFieldMediator = playerPlayFieldMediator;
    }
}
