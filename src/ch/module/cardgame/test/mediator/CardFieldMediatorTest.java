package ch.module.cardgame.test.mediator;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardBuilder;
import ch.module.cardgame.card.CardField;
import ch.module.cardgame.mediator.CardFieldMediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFieldMediatorTest {
    private CardFieldMediator mediator;

    @BeforeEach
    void setUp() {
        mediator = new CardFieldMediator();
    }

    @Test
    void attackWhenBothCardFieldsAreEmpty() {
        mediator.setActingCardField(new CardField());
        mediator.setReceivingCardField(new CardField());
        assertEquals(0, mediator.attackAndCalcPlayerDamage());
    }

    @Test
    void attackWhenDefenderCardFieldIsEmpty() {
        CardField attacker = new CardField();
        int attackPoints = 2;
        attacker.setCard(new CardBuilder().setAttackPoints(attackPoints).build());
        mediator.setActingCardField(attacker);
        mediator.setReceivingCardField(new CardField());
        assertEquals(2, mediator.attackAndCalcPlayerDamage());
    }

    @Test
    void attackWhenBothArePresentAndDefenderSurvives() {
        CardField attacker = new CardField();
        int attackPoints = 2;
        Card card = new CardBuilder().setAttackPoints(attackPoints).setHealthPoints(3).setSummonEnergyPoints(1).build();
        CardField defender = new CardField();
        defender.setCard(card);
        attacker.setCard(card);
        mediator.setActingCardField(attacker);
        mediator.setReceivingCardField(defender);
        assertEquals(0, mediator.attackAndCalcPlayerDamage());
        assertEquals(1, defender.getCard().getHealthPoints());
    }

    @Test
    void attackAndDefenderDies() {
        CardField attacker = new CardField();
        int attackPoints = 2;
        Card card = new CardBuilder().setAttackPoints(attackPoints).setHealthPoints(1).setSummonEnergyPoints(1).build();
        CardField defender = new CardField();
        defender.setCard(card);
        attacker.setCard(card);
        mediator.setActingCardField(attacker);
        mediator.setReceivingCardField(defender);
        assertEquals(0, mediator.attackAndCalcPlayerDamage());
        assertNull(defender.getCard());
    }
}