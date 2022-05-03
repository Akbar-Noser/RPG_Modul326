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
    private boolean hasDrawnCard;

    protected Player(PlayerPlayFieldMediator mediator) {
        this.playerPlayFieldMediator = mediator;
        this.stats = new PlayerStats();
        this.hand = new PlayerHand();
        this.deck = new CardDeck();
        PlayField.getInstance().registerPlayer(this);
    }

    public void endTurn() {
        hasDrawnCard = false;
        finalizeEndTurn();
    }

    protected abstract void finalizeEndTurn();
    /**
     * Adds card from the top of the deck to the hand if the hand doesn't hold the maximum capacity
     */
    public void drawCard () {
        if(!hasDrawnCard && hand.amountOfCardsInHand() < PlayerHand.getMAX_CARDS() && !deck.getCards().isEmpty()) {
            hand.addToHand(deck.popTopCard());
            hasDrawnCard = true;
        }
    }

    /**
     * The player plays a card
     *
     * @param card The card to be played
     * @param index The index of the field on which the card should be played
     * @return true if the card was successfully played, false otherwise
     */
    public boolean playCard(Card card, int index) {
        if (card.getSummonEnergyPoints() > stats.getEnergy())
            return false;
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

    public PlayerPlayFieldMediator getPlayerPlayFieldMediator() {
        return playerPlayFieldMediator;
    }

    public PlayerHand getHand() {
        return hand;
    }

    public void setPlayerPlayFieldMediator(PlayerPlayFieldMediator playerPlayFieldMediator) {
        this.playerPlayFieldMediator = playerPlayFieldMediator;
    }

    public CardDeck getDeck() {
        return deck;
    }
}
