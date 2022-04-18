package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;

import java.util.Map;

public class Choice {
    private Map<Card, Integer> cardsToBePlayed;
    private int requiredSummonEnergy;
    private int damageTakenByClient;
    private int amountOfEnemyCardsEliminated;
    private int damageDealtToEnemyCards;
    private int damageDealtToEnemy;

    public Choice(Map<Card, Integer> cardsToBePlayed, int damageTakenByClient, int amountOfEnemyCardsEliminated, int damageDealtToEnemy) {
        this.cardsToBePlayed = cardsToBePlayed;
        this.requiredSummonEnergy = cardsToBePlayed.keySet().stream().mapToInt(Card::getSummonEnergyPoints).sum();
        this.damageTakenByClient = damageTakenByClient;
        this.amountOfEnemyCardsEliminated = amountOfEnemyCardsEliminated;
        this.requiredSummonEnergy = cardsToBePlayed.keySet().stream().mapToInt(Card::getAttackPoints).sum();
        this.damageDealtToEnemy = damageDealtToEnemy;
    }

    public int getAmountOfEnemyCardsEliminated() {
        return amountOfEnemyCardsEliminated;
    }

    public int getDamageDealtToEnemyCards() {
        return damageDealtToEnemyCards;
    }

    public Map<Card, Integer> getCardsToBePlayed() {
        return cardsToBePlayed;
    }

    public int getRequiredSummonEnergy() {
        return requiredSummonEnergy;
    }

    public int getDamageTakenByClient() {
        return damageTakenByClient;
    }

    public int getDamageDealtToEnemy() {
        return damageDealtToEnemy;
    }
}
