package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardField;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class ChoiceMaker {
    private Player client;
    private List<CardField> enemyField;
    private List<CardField> ownField;
    private List<Integer> fieldsToDefend;
    private List<Integer> openFields;

    public ChoiceMaker(Player client) {
        this.client = client;
        this.enemyField = client.getPlayerPlayFieldMediator().getEnemyPlayfield(client);
        this.ownField = client.getPlayerPlayFieldMediator().getPlayfield().get(client);
    }


    public Choice getOptimalChoice() {
        fieldsToDefend = getUnprotectedFieldIndices();
        if (fieldsToDefend.isEmpty())
            return null;
        List<Choice> choices = new ArrayList<>(generateChoices());
        choices = getChoicesWithMinimalDamageTaken(choices);
        choices = getChoicesWithMaximumDamageDealt(choices);
        choices = getChoicesWithMaximumOfEnemyCardsEliminated(choices);
        choices = getChoicesWithMinimalAmountOfEnergySpent(choices);
        return choices.get(0);
    }

    /**
     * Returns the choices which will result in the least amount of damage taken by the client.
     * Will return the same list as passed if the maximum amount of damage taken is 0.
     *
     * @param choices the choices which should be evaluated.
     * @return returns a list of choices.
     */
    public List<Choice> getChoicesWithMinimalDamageTaken(List<Choice> choices) {
        choices.sort(Comparator.comparing(Choice::getDamageTakenByClient));
        if (choices.get(choices.size() - 1).getDamageTakenByClient() == 0)
            return choices;
        List<Choice> result = new ArrayList<>();
        int lowestDamage = choices.get(0).getDamageTakenByClient();
        result.add(choices.get(0));
        int i = 1;
        while (i < choices.size() && choices.get(i).getDamageTakenByClient() == lowestDamage) {
            result.add(choices.get(i));
            i++;
        }

        return result;
    }

    /**
     * Returns the choices which will result in the least amount of energy spent by the client.
     * Will return the same list as passed if the maximum amount of energy spent is 0.
     *
     * @param choices the choices which should be evaluated.
     * @return returns a list of choices.
     */
    public List<Choice> getChoicesWithMinimalAmountOfEnergySpent(List<Choice> choices) {
        choices.sort(Comparator.comparing(Choice::getRequiredSummonEnergy));
        if (choices.get(choices.size() - 1).getRequiredSummonEnergy() == 0)
            return choices;
        List<Choice> result = new ArrayList<>();
        int lowestEnergy = choices.get(0).getRequiredSummonEnergy();
        result.add(choices.get(0));
        int i = 1;
        while (i < choices.size() && choices.get(i).getRequiredSummonEnergy() == lowestEnergy) {
            result.add(choices.get(i));
            i++;
        }

        return result;
    }

    /**
     * Returns the choices which will result in the maximum amount of damage taken by the enemy.
     * Will return the same list if the maximum amount of damage taken is 0.
     *
     * @param choices The choices which should be evaluated.
     * @return the choices which will result in the highest amount of damage to the enemy.
     */
    public List<Choice> getChoicesWithMaximumDamageDealt(List<Choice> choices) {
        choices.sort(Comparator.comparing(Choice::getDamageDealtToEnemy).reversed());
        if (choices.get(0).getDamageDealtToEnemy() == 0)
            return choices;
        List<Choice> result = new ArrayList<>();
        int highestDamage = choices.get(0).getDamageDealtToEnemy();
        result.add(choices.get(0));
        int i = 1;
        while (i < choices.size() && choices.get(i).getDamageDealtToEnemy() == highestDamage) {
            result.add(choices.get(i));
            i++;
        }
        return result;
    }

    /**
     * Will return the list of choices which will result in the most enemy cards eliminated.
     * Will return the same list if the maximum is 0.
     *
     * @param choices the choices which should be evaluated
     * @return the choices with the highest amount of enemy cards eliminated.
     */
    public List<Choice> getChoicesWithMaximumOfEnemyCardsEliminated(List<Choice> choices) {
        choices.sort(Comparator.comparing(Choice::getAmountOfEnemyCardsEliminated).reversed());
        if (choices.get(0).getAmountOfEnemyCardsEliminated() == 0)
            return choices;
        List<Choice> result = new ArrayList<>();
        int highestDamage = choices.get(0).getAmountOfEnemyCardsEliminated();
        result.add(choices.get(0));
        int i = 1;
        while (i < choices.size() && choices.get(i).getAmountOfEnemyCardsEliminated() == highestDamage) {
            result.add(choices.get(i));
            i++;
        }
        return result;
    }

    /**
     * Generates all the possible choices which can be made.
     *
     * @return a linked list of all the choice objects.
     */
    public List<Choice> generateChoices() {
        openFields = getIndicesOfOpenFields();
        List<List<Card>> cardCombinations = CardCombinator.getUniqueCombinationsOfCards(client.getHand().getCards());
        cardCombinations = filterImpossibleCombinations(cardCombinations);
        List<int[]> possiblePositionsForCards = new LinkedList<>();
        int[] possiblePositions = openFields.stream().mapToInt(Integer::intValue).toArray();
        CardCombinator.getAllPermutations(possiblePositionsForCards, possiblePositions, possiblePositions.length, possiblePositions.length);
        List<Choice> choices = new LinkedList<>();
        for (List<Card> cardCombination : cardCombinations) {
            choices.addAll(generateAllChoicesForCombination(cardCombination, possiblePositionsForCards));
        }
        if (choices.isEmpty())
            choices.add(new Choice(Map.of(), 0,0, 0));
        return choices;
    }

    /**
     * Filters out the combinations which require too much energy or where there aren't enough
     * open cardFields to play all cards.
     *
     * @param combinations the unfiltered combinations.
     * @return The combinations which can possibly be played by the client.
     */
    public List<List<Card>> filterImpossibleCombinations(List<List<Card>> combinations) {
        long amountOfOpenFields = openFields.size();
        return combinations.stream().filter(cards ->
                cards.stream().mapToInt(Card::getSummonEnergyPoints).sum() <= client.getStats().getEnergy()
                        && cards.size() <= amountOfOpenFields).collect(Collectors.toList());
    }

    /**
     * Generates all the possible choice objects when given all the possible positions.
     *
     * @param cards                     the cards that should be placed.
     * @param possiblePositionsForCards the possible positions on which they can be placed.
     * @return a list of all the possible choice objects.
     */
    public List<Choice> generateAllChoicesForCombination(List<Card> cards, List<int[]> possiblePositionsForCards) {
        List<Choice> choices = new LinkedList<>();
        for (int[] possiblePosition : possiblePositionsForCards) {
            Map<Integer, Card> cardsToBePlaced = new HashMap<>();
            for (int i = 0; i < cards.size(); i++) {
                cardsToBePlaced.put(possiblePosition[i], cards.get(i));
            }
            choices.add(generateChoiceForCardPlacement(cardsToBePlaced));
        }
        return choices;
    }

    /**
     * Calculates the outcome for the placement of the cards on certain positions.
     *
     * @param cardsToBePlaced the cards that should be placed and their position.
     * @return the predicted choice object.
     */
    public Choice generateChoiceForCardPlacement(Map<Integer, Card> cardsToBePlaced) {
        int damageTakenByClient = fieldsToDefend.stream().mapToInt(fieldToDefend ->
                cardsToBePlaced.get(fieldToDefend) == null ? enemyField.get(fieldToDefend).getCard().getAttackPoints() : 0).sum();
        int amountOfEnemyCardsEliminated = 0;
        int damageDealtToEnemy = 0;
        List<CardField> futureOwnField = ownField.stream().map(field -> new CardField(field.getCard())).toList();
        cardsToBePlaced.forEach((fieldIndex, card) -> futureOwnField.get(fieldIndex).setCard(card));
        for (int i = 0; i < futureOwnField.size(); i++) {
            Card ownCard = futureOwnField.get(i).getCard();
            Card enemyCard = enemyField.get(i).getCard();
            if (enemyCard == null && ownCard != null) {
                damageDealtToEnemy += ownCard.getAttackPoints();
                continue;
            }
            if (enemyCard != null && ownCard != null && ownCard.getAttackPoints() >= enemyCard.getHealthPoints())
                amountOfEnemyCardsEliminated++;
        }

        return new Choice(cardsToBePlaced, damageTakenByClient, amountOfEnemyCardsEliminated, damageDealtToEnemy);
    }

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

    private List<Integer> getIndicesOfOpenFields() {
        List<Integer> indices = new ArrayList<>(PlayField.getMaxAmountCardFields());
        for (int i = 0; i < ownField.size(); i++) {
            if (ownField.get(i).getCard() == null)
                indices.add(i);
        }
        return indices;
    }

    public void setEnemyField(List<CardField> enemyField) {
        this.enemyField = enemyField;
    }

    public void setOwnField(List<CardField> ownField) {
        this.ownField = ownField;
    }

    public void setFieldsToDefend(List<Integer> fieldsToDefend) {
        this.fieldsToDefend = fieldsToDefend;
    }
}
