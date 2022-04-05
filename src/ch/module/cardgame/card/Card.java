package ch.module.cardgame.card;

public class Card {

    private int healthPoints;

    private int attackPoints;

    private int summonEnergyPoints;

    public Card(CardBuilder cardBuilder) {
        this.healthPoints = cardBuilder.getHealthPoints();
        this.attackPoints = cardBuilder.getAttackPoints();
        this.summonEnergyPoints = cardBuilder.getSummonEnergyPoints();
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }
    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getSummonEnergyPoints() {
        return summonEnergyPoints;
    }
    public void setSummonEnergyPoints(int summonEnergyPoints) {
        this.summonEnergyPoints = summonEnergyPoints;
    }

    @Override
    public String toString() {
        return "Card{" +
                "healthPoints=" + healthPoints +
                ", attackPoints=" + attackPoints +
                ", summonEnergyPoints=" + summonEnergyPoints +
                '}';
    }
}
