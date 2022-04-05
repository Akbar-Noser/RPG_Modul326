package ch.module.cardgame.cards;

public class CardBuilder {

    private int healthPoints;

    private int attackPoints;

    private int summonEnergyPoints;

    public CardBuilder setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
        return this;
    }

    public CardBuilder setAttackPoints(int attackPoints){
        this.attackPoints = attackPoints;
        return this;
    }

    public CardBuilder setSummonEnergyPoints(int summonEnergyPoints){
        this.summonEnergyPoints = summonEnergyPoints;
        return this;
    }

    /**
     *
     * @return
     */
    public Card build(){
        return new Card(this);
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public int getAttackPoints() {
        return attackPoints;
    }
    public int getSummonEnergyPoints() {
        return summonEnergyPoints;
    }
}
