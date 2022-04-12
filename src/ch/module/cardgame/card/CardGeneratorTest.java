package ch.module.cardgame.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardGeneratorTest {
    private final CardGenerator generator = CardGenerator.getInstance();

    @Test
    void checkThatSummonEnergyIsCalculatedCorrectly() {
        Card randomCard = generator.generateRandomCard();
        assertEquals((randomCard.getAttackPoints() + randomCard.getHealthPoints()) / 2, randomCard.getSummonEnergyPoints());
    }
}