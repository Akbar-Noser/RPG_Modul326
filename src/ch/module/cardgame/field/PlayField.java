package ch.module.cardgame.field;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardField;
import ch.module.cardgame.mediator.CardFieldMediator;
import ch.module.cardgame.player.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton class for representing the actual play board or field.
 */
public class PlayField {

    private static final PlayField instance = new PlayField();
    private static final int MAX_AMOUNT_CARD_FIELDS = 4; // 4 x 4
    private Map<Player, List<CardField>> cardFields;
    private List<CardFieldMediator> cardSlotMediators;

    //TODO: Add a registerPlayer method to register a player's fields
    public PlayField() {
        cardFields = new HashMap<>();
        cardSlotMediators = new ArrayList<>(MAX_AMOUNT_CARD_FIELDS);
        cardSlotMediators.addAll(Collections.nCopies(MAX_AMOUNT_CARD_FIELDS, new CardFieldMediator()));
    }

    /**
     * Attack method which launches the mediator. It will calculate the damage between the cards
     * if the cards exist. It will return the player damage.
     *
     * @param attacker      Current player attacking
     * @param defender      The player receiving the attack
     * @param index         Index for which card field was chosen
     * @return The damage which the defender will receive.
     */
    public int attackOppositeField(Player attacker, Player defender, int index) {
        CardFieldMediator slotMediator = cardSlotMediators.get(index);
        slotMediator.setActingCardField(cardFields.get(attacker).get(index));
        slotMediator.setReceivingCardField(cardFields.get(defender).get(index));
        return slotMediator.attackAndCalcPlayerDamage();
    }

    /**
     * Attack Method which delegates the attack to the corresponding card field mediator and for all.
     *
     * @param attacker        Current player attacking
     * @param defender        Player receiving the attack.
     */
    public int attackAllOppositeFields(Player attacker, Player defender) {
        int damageSum = 0;
        for (int i = 0; i < MAX_AMOUNT_CARD_FIELDS; i++) {
            damageSum += attackOppositeField(attacker, defender, i);
        }
        return damageSum;
    }

    /**
     * Registers a player and assigns cardFields to him
     *
     * @param player the player who should be registered
     */
    public void registerPlayer(Player player) {
        cardFields.put(player, Stream.generate(CardField::new).limit(MAX_AMOUNT_CARD_FIELDS).toList());
    }

    /**
     * Plays a card on a field of a player. Returns false if the field is already occupied.
     *
     * @param initiator the player on whose side a card should be played
     * @param card the card which should be played
     * @param index the index of the field where the card should be played
     * @return true if the card was successfully played, false otherwise
     */
    public boolean playCardOnField(Player initiator, Card card, int index) {
        CardField targetField = cardFields.get(initiator).get(index);
        if (targetField.getCard() != null)
            return false;
        targetField.setCard(card);
        return true;
    }

    public Map<Player, List<CardField>> getCardFields() {
        return cardFields;
    }

    public static int getMaxAmountCardFields() {
        return MAX_AMOUNT_CARD_FIELDS;
    }

    /**
     * Retrieves the play field singleton instance.
     *
     * @return              this objects instance
     */
    public static PlayField getInstance() {
        return instance;
    }
}