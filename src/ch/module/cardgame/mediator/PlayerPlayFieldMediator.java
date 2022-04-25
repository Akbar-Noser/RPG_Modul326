package ch.module.cardgame.mediator;


import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardField;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.player.Player;

import java.util.List;
import java.util.Map;

/**
 * Mediator class following the GoF Mediator pattern. It constitutes as an intersection between the play field and an actual player
 */
public class PlayerPlayFieldMediator {

    private Player playerUser;
    private Player playerAI;

    public PlayerPlayFieldMediator() {
    }

    public PlayerPlayFieldMediator(Player playerAI, Player playerUser) {
        this.playerAI = playerAI;
        this.playerUser = playerUser;
    }

    /**
     * Controls the interaction that happens when a player ends his turn.
     * The player who ended his turn will attack with all the placed cards.
     *
     * @param initiator the player who ended his turn
     */
    public void endTurn(Player initiator) {
        Player defender = getOtherPlayer(initiator);
        int damage = PlayField.getInstance().attackAllOppositeFields(initiator, defender);
        dealDamage(defender, damage);
        initiator.getStats().incrementEnergyBy(2);
    }

    /**
     * Deals damage to a player and ends the game if that player would die from the attack.
     * The other player is declared as the winner if that is the case.
     *
     * @param receiver The player who receives the damage.
     * @param damagePoints The amount of damage dealt.
     */
    private void dealDamage(Player receiver, int damagePoints) {
        if (receiver.getStats().getHealth() - damagePoints <= 0)
            endGame(getOtherPlayer(receiver));
        receiver.getStats().decrementHealthBy(damagePoints);
    }

    public void endGame(Player winner) {
        //TODO: Implement winning interaction/screen
    }

    public boolean playCard(Player initiator, Card card, int fieldIndex) {
        boolean successful = PlayField.getInstance().playCardOnField(initiator, card, fieldIndex);
        if (successful) {
            initiator.removeCardFromHand(card);
        }
        return successful;
    }

    public Map<Player, List<CardField>> getPlayfield() {
        return PlayField.getInstance().getCardFields();
    }

    public List<CardField> getEnemyPlayfield(Player initiator) {
        if (initiator.equals(playerAI))
            return PlayField.getInstance().getCardFields().get(playerUser);
        return PlayField.getInstance().getCardFields().get(playerAI);
    }

    public void setPlayerUser(Player playerUser) {
        this.playerUser = playerUser;
    }

    public Player getOtherPlayer(Player currentPlayer) {
        return currentPlayer == playerAI ? playerUser : playerAI;
    }

    public void setPlayerAI(Player playerAI) {
        this.playerAI = playerAI;
    }
}
