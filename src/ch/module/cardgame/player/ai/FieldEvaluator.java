package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.CardField;
import ch.module.cardgame.field.PlayField;

import java.util.ArrayList;
import java.util.List;

public record FieldEvaluator(List<CardField> ownField,
                             List<CardField> enemyField) {

    /**
     * Gets the field indices where there is an enemy card and no own card to block it.
     *
     * @return the unprotected indices.
     */
    public List<Integer> getUnprotectedFieldIndices() {
        List<Integer> unprotectedFields = new ArrayList<>(PlayField.getMaxAmountCardFields());
        for (int i = 0; i < enemyField.size(); i++) {
            if (fieldIsUnprotected(i))
                unprotectedFields.add(i);
        }

        return unprotectedFields;
    }

    /**
     * Checks if a field is unprotected
     *
     * @param index the index of the field to be checked
     * @return a boolean value which expresses if the field is unprotected.
     */
    public boolean fieldIsUnprotected(int index) {
        CardField enemyCardField = enemyField.get(index);
        return enemyCardField.getCard() != null && ownField.get(index).getCard() == null && enemyCardField.getCard().getAttackPoints() > 0;
    }

    public List<Integer> getIndicesOfOpenFields() {
        List<Integer> indices = new ArrayList<>(PlayField.getMaxAmountCardFields());
        for (int i = 0; i < ownField.size(); i++) {
            if (ownField.get(i).getCard() == null)
                indices.add(i);
        }
        return indices;
    }
}
