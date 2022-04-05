package ch.module.cardgame.player;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatsTest {
    private PlayerStats stats;

    @BeforeEach
    void setUp() {
        stats = new PlayerStats();
    }

    @Test
    void decrementHealthBy() {
        // GIVEN
        int valueToDecrement = 5;

        // WHEN
        stats.decrementHealthBy(valueToDecrement);

        // THEN
        assertEquals(stats.getMaxHealth() - valueToDecrement, stats.getHealth());
    }

    @Test
    void decrementHealthToNegativeNumber() {
        // GIVEN
        int valueToDecrement = 5;
        stats.setHealth(0);

        // WHEN
        stats.decrementHealthBy(valueToDecrement);

        // THEN
        assertEquals(0, stats.getHealth());
    }

    @Test
    void incrementHealth() {
        // GIVEN
        int valueToIncrement = 5;
        stats.setHealth(0);

        // WHEN
        stats.incrementHealthBy(valueToIncrement);

        assertEquals(valueToIncrement, stats.getHealth());
    }

    @Test
    void incrementHealthToAboveMax() {
        // GIVEN
        int valueToIncrement = 5;

        // WHEN
        stats.incrementHealthBy(valueToIncrement);

        // THEN
        assertEquals(stats.getMaxHealth(), stats.getHealth());
    }

    @Test
    void decrementEnergyBy() {
        // GIVEN
        int valueToDecrement = 5;

        // WHEN
        stats.decrementEnergyBy(valueToDecrement);

        // THEN
        assertEquals(stats.getMaxEnergy() - valueToDecrement, stats.getEnergy());
    }

    @Test
    void decrementEnergyToNegativeNumber() {
        // GIVEN
        int valueToDecrement = 5;
        stats.setEnergy(0);

        // WHEN
        stats.decrementEnergyBy(valueToDecrement);

        // THEN
        assertEquals(0, stats.getEnergy());
    }

    @Test
    void incrementEnergy() {
        // GIVEN
        int valueToIncrement = 5;
        stats.setEnergy(0);

        // WHEN
        stats.incrementEnergyBy(valueToIncrement);

        // THEN
        assertEquals(valueToIncrement, stats.getEnergy());
    }

    @Test
    void incrementEnergyToAboveMax() {
        // GIVEN
        int valueToIncrement = 5;

        // WHEN
        stats.incrementEnergyBy(valueToIncrement);

        // THEN
        assertEquals(stats.getMaxEnergy(), stats.getEnergy());
    }
}