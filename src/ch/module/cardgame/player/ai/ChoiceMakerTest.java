package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardBuilder;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChoiceMakerTest {
    private ChoiceMaker choiceMaker;
    private PlayerPlayFieldMediator playerPlayFieldMediator = new PlayerPlayFieldMediator();
    private Player ai = new EnemyAi(playerPlayFieldMediator);
    private Player user = new User();

    @BeforeEach
    void setUp() {
        playerPlayFieldMediator.setPlayerUser(user);
        playerPlayFieldMediator.setPlayerAI(ai);
        choiceMaker = new ChoiceMaker(ai);
        choiceMaker.setFieldsToDefend(List.of(0,1,2,3,4));
        PlayField.getInstance().registerPlayer(ai);
        PlayField.getInstance().registerPlayer(user);
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
    }

    @Test
    void getUnprotectedFieldIndices() {
    }

    @Test
    void fieldIsUnprotected() {
    }
}