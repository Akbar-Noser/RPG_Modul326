package ch.module.cardgame.player;

import ch.module.cardgame.mediator.PlayerPlayFieldMediator;

public class Player {
    private PlayerStats stats;
    private PlayerHand hand;
    //TODO: Add CardDeck here
    private PlayerPlayFieldMediator playerPlayFieldMediator;

    public Player() {
        this.stats = new PlayerStats();
        this.hand = new PlayerHand();
    }

    public void endTurn() {
        playerPlayFieldMediator.endTurn();
    }

    public PlayerStats getStats() {
        return stats;
    }


}
