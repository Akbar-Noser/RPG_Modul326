package ch.module.cardgame.card;

import jdk.jfr.Label;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    private final int TEST_HEALTHPOINTS = 20;
    private final int TEST_ATTACKPOINTS = 20;
    private final int TEST_ATTACKPOINTS_FALSE = 5;
    private final int TEST_SUMMONENERGYPOINTS = 9;

    @Label("Comparing card health points - expecting: true")
    @Test
    void compareHealthpoints() {
        Card testCard = new CardBuilder().setHealthPoints(TEST_HEALTHPOINTS).build();
        assertEquals(TEST_HEALTHPOINTS, testCard.getHealthPoints());
    }

    @Label("Comparing card summon energy - expecting: true")
    @Test
    void compareCardObject() {
        Card testCard = new CardBuilder().setSummonEnergyPoints(TEST_SUMMONENERGYPOINTS).build();
        assertEquals(TEST_SUMMONENERGYPOINTS, testCard.getSummonEnergyPoints());
    }

    @Label("Comparing false card attack points - expecting: false")
    @Test
    void compareFalseAttackPoints() {
        Card testCard = new CardBuilder().setHealthPoints(TEST_HEALTHPOINTS).setAttackPoints(TEST_ATTACKPOINTS_FALSE).build();
        assertNotEquals(TEST_ATTACKPOINTS, testCard.getAttackPoints());
    }
}