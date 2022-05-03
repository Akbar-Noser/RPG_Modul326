package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardBuilder;
import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceMakerTest {

    private PlayerPlayFieldMediator playerPlayFieldMediator = new PlayerPlayFieldMediator();
    private Player user = new User(playerPlayFieldMediator);
    private Player ai = new EnemyAi(playerPlayFieldMediator);
    private ChoiceMaker choiceMaker = ((EnemyAi) ai).getChoiceMaker();

    @BeforeEach
    void setUp() {
        choiceMaker.setFieldsToDefend(List.of(0,1,2,3));
    }

    @Test
    void getChoicesWithMinimalDamageTaken() {
        Choice lowestChoice = new Choice(Map.of(), 0, 1, 2);
        Choice lowestChoice2 = new Choice(Map.of(), 0, 1, 2);
        List<Choice> result = choiceMaker.getChoicesWithMinimalDamageTaken(new ArrayList<>(List.of(lowestChoice, lowestChoice2,
                new Choice(Map.of(), 2, 1, 2))));
        assertEquals(List.of(lowestChoice, lowestChoice2), result);
    }

    @Test
    void getChoicesWithMinimalAmountOfEnergySpent() {
        Choice lowestChoice = new Choice(Map.of(), 0, 1, 2);
        Choice lowestChoice2 = new Choice(Map.of(), 0, 1, 2);
        List<Choice> result = choiceMaker.getChoicesWithMinimalDamageTaken(new ArrayList<>(List.of(lowestChoice, lowestChoice2,
                new Choice(Map.of(1, new CardBuilder().setSummonEnergyPoints(1).build()), 2, 1, 2))));
        assertEquals(List.of(lowestChoice, lowestChoice2), result);
    }

    @Test
    void getChoicesWithMaximumDamageDealt() {
        Choice lowestChoice = new Choice(Map.of(), 0, 1, 2);
        Choice lowestChoice2 = new Choice(Map.of(), 0, 1, 2);
        List<Choice> result = choiceMaker.getChoicesWithMinimalDamageTaken(new ArrayList<>(List.of(lowestChoice, lowestChoice2,
                new Choice(Map.of(), 2, 1, 1))));
        assertEquals(List.of(lowestChoice, lowestChoice2), result);
    }

    @Test
    void getChoicesWithMaximumOfEnemyCardsEliminated() {
        Choice lowestChoice = new Choice(Map.of(), 0, 1, 2);
        Choice lowestChoice2 = new Choice(Map.of(), 0, 1, 2);
        List<Choice> result = choiceMaker.getChoicesWithMinimalDamageTaken(new ArrayList<>(List.of(lowestChoice, lowestChoice2,
                new Choice(Map.of(), 2, 0, 1))));
        assertEquals(List.of(lowestChoice, lowestChoice2), result);
    }

    @Test
    void filterImpossibleCombinations() {
        ai.getStats().setEnergy(5);
        CardBuilder builder = new CardBuilder().setSummonEnergyPoints(2);
        List<Card> correctCombination1 = List.of(builder.build());
        List<Card> correctCombination2 = List.of(builder.build(), builder.build(), builder.setSummonEnergyPoints(1).build());
        List<List<Card>> combinations = new ArrayList<>(List.of(correctCombination1, correctCombination2,
                List.of(builder.setSummonEnergyPoints(0).build(), builder.build(), builder.build(), builder.build(), builder.build(), builder.build()),
                List.of(builder.setSummonEnergyPoints(3).build(), builder.build())));
        assertEquals(List.of(correctCombination1, correctCombination2),
                choiceMaker.filterImpossibleCombinations(combinations));
    }

    @Test
    void generateChoiceForCardPlacement() {
        choiceMaker.setFieldsToDefend(List.of(0,1));
        CardBuilder builder = new CardBuilder().setSummonEnergyPoints(3).setAttackPoints(3).setHealthPoints(2);
        Card cardToBePlaced = builder.build();
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(0).setCard(builder.build());
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(1).setCard(builder.build());
        Choice result = choiceMaker.generateChoiceForCardPlacement(Map.of(0, cardToBePlaced));
        assertEquals(1, result.getAmountOfEnemyCardsEliminated());
        assertEquals(0, result.getDamageDealtToEnemy());
        assertEquals(3, result.getDamageTakenByClient());
        assertEquals(Map.of(0, cardToBePlaced), result.getCardsToBePlayed());
        assertEquals(3, result.getRequiredSummonEnergy());
        assertEquals(3, result.getDamageDealtToEnemyCards());
    }

    @Test
    void getUnprotectedFieldIndices() {
        CardBuilder builder = new CardBuilder().setSummonEnergyPoints(3).setAttackPoints(3).setHealthPoints(2);
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(0).setCard(builder.build());
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(1).setCard(builder.build());
        ai.getPlayerPlayFieldMediator().getPlayField().get(ai).get(0).setCard(builder.build());
        assertEquals(List.of(1),choiceMaker.getUnprotectedFieldIndices());
    }

    @Test
    void fieldIsUnprotected() {
        CardBuilder builder = new CardBuilder().setSummonEnergyPoints(3).setAttackPoints(3).setHealthPoints(2);
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(0).setCard(builder.build());
        ai.getPlayerPlayFieldMediator().getEnemyPlayField(ai).get(1).setCard(builder.build());
        ai.getPlayerPlayFieldMediator().getPlayField().get(ai).get(0).setCard(builder.build());
        assertFalse(choiceMaker.fieldIsUnprotected(0));
        assertTrue(choiceMaker.fieldIsUnprotected(1));
    }
}