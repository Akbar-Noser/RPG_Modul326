package ch.module.cardgame.mediator;


import ch.module.cardgame.player.Player;

/**
 * Mediator class following the GoF Mediator pattern. It constitutes as an intersection between the play field and an actual player
 */
public class PlayerPlayFieldMediator {

    private Player playerUser;
    private Player playerAI;

    public PlayerPlayFieldMediator(Player playerAI, Player playerUser) {
        this.playerAI = playerAI;
        this.playerUser = playerUser;
    }

    public void endTurn() {
        //TODO pick which player has ended the turn :  dealDamage(PlayField.getInstance().attackAllOppositeFields(playerAI, playerUser));
    }

    public void dealDamage(int damagePoints) {
        //TODO pick which player has attacked and then calculate the dmg :  player.getStats().setHealth(player.getStats().getHealth() - damagePoints);
    }



}
