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

    public void endTurn(Player initiator) {
        //TODO pick which player has ended the turn :  dealDamage(PlayField.getInstance().attackAllOppositeFields(playerAI, playerUser));
    }

    public void dealDamage(Player initiator, int damagePoints) {
        //TODO pick which player has attacked and then calculate the dmg :  player.getStats().setHealth(player.getStats().getHealth() - damagePoints);
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

    public void setPlayerAI(Player playerAI) {
        this.playerAI = playerAI;
    }
}
