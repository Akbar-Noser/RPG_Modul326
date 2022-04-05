package ch.module.cardgame.card;

import java.util.concurrent.ThreadLocalRandom;

public class CardGenerator {
    private final int MAX_HEALTH;
    private final int MAX_ATTACK;
    private final CardBuilder cardBuilder;

    public CardGenerator() {
        this.MAX_HEALTH = 10;
        this.MAX_ATTACK = 10;
        this.cardBuilder = new CardBuilder();
    }

    public Card generateRandomCard() {
        int attack = ThreadLocalRandom.current().nextInt(MAX_ATTACK + 1);
        int health = ThreadLocalRandom.current().nextInt(MAX_HEALTH + 1);
        int summonEnergy = calcSummonEnergy(health, attack);
        return cardBuilder.setAttackPoints(attack).setHealthPoints(health).setSummonEnergyPoints(summonEnergy).build();
    }

    private int calcSummonEnergy(int health, int attack) {

    }
}