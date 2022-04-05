package ch.module.cardgame.player;

public class Player {
    private PlayerStats stats;
    private PlayerHand hand;
    //TODO: Add CardDeck here
    //TODO: Add PlayerPlayFieldMediator

    public Player() {
        this.stats = new PlayerStats();
        this.hand = new PlayerHand();
    }

    public void endTurn() {
        //TODO: Call PlayerPlayFieldMediator here
    }

    public PlayerStats getStats() {
        return stats;
    }


}
