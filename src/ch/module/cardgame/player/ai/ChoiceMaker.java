package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardField;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChoiceMaker {
    private Player client;
    private List<CardField> enemyField;
    private List<CardField> ownField;
    private List<Integer> fieldsToDefend;

    public ChoiceMaker(Player client) {
        this.client = client;
        this.enemyField = client.getPlayerPlayFieldMediator().getEnemyPlayfield(client);
        this.ownField = client.getPlayerPlayFieldMediator().getPlayfield().get(client);
    }

    /**
     * Returns the choices which will result in the least amount of damage taken by the client. Returns null if there
     * are no enemy cards that pose a threat.
     *
     * @return returns a list of choices or null if no enemy cards pose a threat.
     */
    public List<Choice> getChoicesWithMinimalDamageTaken() {
        List<Choice> result = new ArrayList<>();
        fieldsToDefend = getUnprotectedFieldIndices();
        if (fieldsToDefend.isEmpty())
            return null;

        return result;
    }

    /*public Choice evaluateChoiceForField(CardField field, int targetIndex) {
        //TODO: Create method to evaluate the possible playable card combinations.

        return new Choice(Map.of(field, targetIndex), );
    }

    public List<List<Card>> getPlayableCardCombinations() {
        List<List<Card>> result = new ArrayList<>();

    }*/

    public List<Integer> getUnprotectedFieldIndices() {
        List<Integer> unprotectedFields = new ArrayList<>(PlayField.getMaxAmountCardFields());
        for (int i = 0; i < enemyField.size(); i++) {
            CardField field = enemyField.get(i);
            if (fieldIsUnprotected(field, i))
                unprotectedFields.add(i);
        }

        return unprotectedFields;
    }

    public boolean fieldIsUnprotected(CardField field, int  index) {
        return field.getCard() != null && ownField.get(index).getCard() == null && field.getCard().getAttackPoints() > 0;
    }
}
