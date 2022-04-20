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

    public List<Choice> generateChoices() {
        List<List<Card>> cardCombinations = CardCombinator.getUniqueCombinationsOfCards(client.getHand().getCards());
        cardCombinations = filterImpossibleCombinations(cardCombinations);
        List<int[]> possiblePositionsForCards = new LinkedList<>();
        int[] possiblePositions = fieldsToDefend.stream().mapToInt(Integer::intValue).toArray();
        CardCombinator.getAllPermutations(possiblePositionsForCards, possiblePositions, possiblePositions.length, possiblePositions.length);
        List<Choice> choices = new LinkedList<>();
        for (List<Card> cardCombination : cardCombinations) {
            choices.addAll(generateAllChoicesForCombination(cardCombination, possiblePositionsForCards));
        }
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
        long openFields = PlayField.getMaxAmountCardFields() - ownField.stream().filter(cardField -> cardField.getCard() != null).count();
        return combinations.stream().filter(cards ->
                cards.stream().mapToInt(Card::getSummonEnergyPoints).sum() > client.getStats().getEnergy()
                        || cards.size() > openFields).collect(Collectors.toList());
    }

    /**
     * Generates all the possible choice objects when given all the possible positions.
     *
     * @param cards the cards that should be placed.
     * @param possiblePositionsForCards the possible positions on which they can be placed.
     * @return a list of all the possible choice objects.
     */
    public List<Choice> generateAllChoicesForCombination(List<Card> cards, List<int[]> possiblePositionsForCards) {
        List<Choice> choices = new LinkedList<>();
        for (int[] possiblePosition : possiblePositionsForCards) {
            Map<Integer, Card> cardsToBePlaced = new HashMap<>();
            for (int i = 0; i < possiblePosition.length; i++) {
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
        List<CardField> futureOwnField = List.copyOf(ownField);
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
            CardField field = enemyField.get(i);
            if (fieldIsUnprotected(field, i))
                unprotectedFields.add(i);
        }

        return unprotectedFields;
    }

    public boolean fieldIsUnprotected(CardField field, int index) {
        return field.getCard() != null && ownField.get(index).getCard() == null && field.getCard().getAttackPoints() > 0;
    }
}
