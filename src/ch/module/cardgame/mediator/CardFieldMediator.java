package ch.module.cardgame.mediator;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardField;

public class CardFieldMediator {
    private CardField actingCardField;
    private CardField receivingCardField;
    
    /**
     * Calculates the damage which the player on the receiving end will get when two CardFields clash.
     * It also manipulates the cards on top of the CardField.
     * 
     * @return the damage which the player on the receiving end will receive.
     */
    public int attackAndCalcPlayerDamage() {
        Card attacker = actingCardField.getCard();
        Card defender = receivingCardField.getCard();
        if (attacker == null)
            return 0;
        if (defender == null)
            return attacker.getAttackPoints();
        int cardDamage = attacker.getAttackPoints();
        defender.setHealthPoints(defender.getHealthPoints() - cardDamage);
        if (defender.getHealthPoints() < 1)
            defender = null;
        receivingCardField.setCard(defender);
        return 0;
    }

    public void setActingCardField(CardField actingCardField) {
        this.actingCardField = actingCardField;
    }

    public void setReceivingCardField(CardField receivingCardField) {
        this.receivingCardField = receivingCardField;
    }
}
