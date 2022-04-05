package ch.module.cardgame.player;

public class Player {
    private PlayerStats stats;
    //TODO: Add CardDeck here
    //TODO: Add PlayerHand here
    //TODO: Add PlayerPlayFieldMediator

    public Player() {
        this.stats = new PlayerStats();
    }

    public void endTurn() {
        //TODO: Call PlayerPlayFieldMediator here
    }

    public PlayerStats getStats() {
        return stats;
    }
}
