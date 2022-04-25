package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;

import java.util.Map;

public class Choice {
    private Map<Integer, Card> cardsToBePlayed;
    private int requiredSummonEnergy;
    private int damageTakenByClient;
    private int amountOfEnemyCardsEliminated;
    private int damageDealtToEnemyCards;
    private int damageDealtToEnemy;

    public Choice(Map<Integer, Card> cardsToBePlayed, int damageTakenByClient, int amountOfEnemyCardsEliminated, int damageDealtToEnemy) {
        this.cardsToBePlayed = cardsToBePlayed;
        this.requiredSummonEnergy = cardsToBePlayed.values().stream().mapToInt(Card::getSummonEnergyPoints).sum();
        this.damageTakenByClient = damageTakenByClient;
        this.amountOfEnemyCardsEliminated = amountOfEnemyCardsEliminated;
        this.damageDealtToEnemyCards = cardsToBePlayed.values().stream().mapToInt(Card::getAttackPoints).sum() - damageDealtToEnemy;
        this.damageDealtToEnemy = damageDealtToEnemy;
    }

    public int getAmountOfEnemyCardsEliminated() {
        return amountOfEnemyCardsEliminated;
    }

    public int getDamageDealtToEnemyCards() {
        return damageDealtToEnemyCards;
    }

    public Map<Integer, Card> getCardsToBePlayed() {
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
