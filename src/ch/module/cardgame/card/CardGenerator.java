package ch.module.cardgame.card;

import java.util.concurrent.ThreadLocalRandom;

public class CardGenerator {
    private final int MAX_HEALTH;
    private final int MAX_ATTACK;
    private final CardBuilder cardBuilder;
    private static final CardGenerator instance = new CardGenerator();

    public CardGenerator() {
        this.MAX_HEALTH = 10;
        this.MAX_ATTACK = 10;
        this.cardBuilder = new CardBuilder();
    }

    /**
     * Returns the instance of the singleton
     *
     * @return the instance
     */
    public static CardGenerator getInstance() {
        return instance;
    }

    /**
     * Generates a card with random values.
     *
     * @return The generated card.
     */
    public Card generateRandomCard() {
        int attack = ThreadLocalRandom.current().nextInt(MAX_ATTACK + 1);
        int health = ThreadLocalRandom.current().nextInt(MAX_HEALTH + 1);
        int summonEnergy = calcSummonEnergy(health, attack);
        return cardBuilder.setAttackPoints(attack).setHealthPoints(health).setSummonEnergyPoints(summonEnergy).build();
    }

    /**
     * The summonenergy is calculated by adding the attack and health stat and dividing the result
     * by 2. The result is always rounded down, so 5.5 will result in summonenergy 5.
     *
     * @param health The health stat
     * @param attack The attack stat
     * @return the required summonenergy
     */
    private int calcSummonEnergy(int health, int attack) {
        return (health + attack) / 2;
    }
}