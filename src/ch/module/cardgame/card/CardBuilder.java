package ch.module.cardgame.card;

public class CardBuilder {

    private int healthPoints;

    private int attackPoints;

    private int summonEnergyPoints;

    /**
     * Builder method for setting the health points of a Card when creating a Card object.
     *
     * @param healthPoints              int representing a card's health points
     * @return                          the current cardBuilder
     */
    public CardBuilder setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
        return this;
    }

    /**
     * Builder method for setting the attack points of a Card when creating a Card object.
     *
     * @param attackPoints              int representing a card's attack points
     * @return                          the current cardBuilder
     */
    public CardBuilder setAttackPoints(int attackPoints){
        this.attackPoints = attackPoints;
        return this;
    }

    /**
     * Builder method for setting the summon energy of a Card when creating a Card object.
     *
     * @param summonEnergyPoints        int representing a card's summon energy
     * @return                          the current cardBuilder
     */
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
