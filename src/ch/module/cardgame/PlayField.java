package ch.module.cardgame;

import ch.module.cardgame.player.Player;

import java.util.HashMap;

/**
 * Singleton class for representing the actual play board or field.
 */
public class PlayField {

    private static final PlayField instance = new PlayField();
    private final int MAX_AMOUNT_CARD_FIELDS = 4; // 4 x 4
    //TODO add private HashMap<Player, List<CardField>> cardFields;
    //TODO add private List<CardFieldMediator> cardFieldMediators;

    public PlayField() {
        //TODO initialize cardFieldMediators
    }

    /**
     * Attack Method which delegates the attack to the corresponding card field mediator and the card field.
     *
     * @param player        Current player attacking
     * @param index         Index for which card field was chosen
     */
    public void attackOppositeField(Player player, int index) {
        //TODO use player as Key for Hashma
    }

    /**
     * Attack Method which delegates the attack to the corresponding card field mediator and for all.
     *
     * @param player        Current player attacking
     */
    public int attackAllOppositeFields(Player player) {
        //TODO use player as Key for Hashmap
        return 0;
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
