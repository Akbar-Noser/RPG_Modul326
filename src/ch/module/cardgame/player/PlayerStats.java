package ch.module.cardgame.player;

public class PlayerStats {
    private final int MAX_HEALTH;
    private final int MAX_ENERGY;
    private int health;
    private int energy;

    public PlayerStats() {
        this.MAX_HEALTH = 100;
        this.MAX_ENERGY = 10;
        health = MAX_HEALTH;
        energy = MAX_ENERGY;
    }

    /**
     * Decrements the health by a certain value and returns
     * the new health value.
     *
     * @param valueToSubtract The value which should be subtracted from the current health.
     * @return New health value after subtraction.
     */
    public int decrementHealthBy(int valueToSubtract) {
        if (health - valueToSubtract < 0)
            return 0;
        health -= valueToSubtract;
        return health;
    }

    /**
     * Increments the health by a certain value and returns the
     * new health value.
     *
     * @param valueToAdd The value which should be added to the current health.
     * @return New health value after addition.
     */
    public int incrementHealthBy(int valueToAdd) {
        if (health + valueToAdd > MAX_HEALTH)
            return MAX_HEALTH;
        health += valueToAdd;
        return health;
    }

    /**
     * Decrements the energy by a certain value and returns
     * the new energy value.
     *
     * @param valueToSubtract The value which should be subtracted from the current energy.
     * @return New energy value after subtraction
     */
    public int decrementEnergyBy(int valueToSubtract) {
        if (energy - valueToSubtract < 0)
            return 0;
        energy -= valueToSubtract;
        return energy;
    }

    /**
     * Increments the energy by a certain value and returns the
     * new energy value.
     *
     * @param valueToAdd The value which should be added to the current energy.
     * @return New energy value after addition.
     */
    public int incrementEnergyBy(int valueToAdd) {
        if (energy + valueToAdd > MAX_ENERGY) {
            energy = MAX_ENERGY;
            return MAX_ENERGY;
        }
        energy += valueToAdd;
        return energy;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
