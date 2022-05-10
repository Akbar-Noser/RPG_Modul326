package ch.module.cardgame.test.card;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {
    private final CardFactory generator = CardFactory.getInstance();

    @Test
    void checkThatSummonEnergyIsCalculatedCorrectly() {
        Card randomCard = generator.generateRandomCard();
        assertEquals((randomCard.getAttackPoints() + randomCard.getHealthPoints()) / 2, randomCard.getSummonEnergyPoints());
    }
}