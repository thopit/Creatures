package de.thomas.creatures.implementation.statistics;

public class StatElement {
    private final int creatureAmount;
    private final double averageLife;
    private final double averageMaxLife;
    private final double averageEnergy;
    private final double averageMaxEnergy;
    private final double averageSpeed;
    private final double averageVisionRange;
    private final double averageMatingEnergyNeeded;
    private final double averageBreedLength;
    private final double averageBreedProgressSpeed;
    private final double genderRatio;
    private final double pregnancyRatio;

    public StatElement(int creatureAmount, double averageLife,
                       double averageMaxLife, double averageEnergy,
                       double averageMaxEnergy, double averageSpeed,
                       double averageVisionRange, double averageMatingEnergyNeeded,
                       double averageBreedLength, double averageBreedProgressSpeed,
                       double genderRatio, double pregnancyRatio) {
        super();
        this.creatureAmount = creatureAmount;
        this.averageLife = averageLife;
        this.averageMaxLife = averageMaxLife;
        this.averageEnergy = averageEnergy;
        this.averageMaxEnergy = averageMaxEnergy;
        this.averageSpeed = averageSpeed;
        this.averageVisionRange = averageVisionRange;
        this.averageMatingEnergyNeeded = averageMatingEnergyNeeded;
        this.averageBreedLength = averageBreedLength;
        this.averageBreedProgressSpeed = averageBreedProgressSpeed;
        this.genderRatio = genderRatio;
        this.pregnancyRatio = pregnancyRatio;
    }

    public int getCreatureAmount() {
        return creatureAmount;
    }

    public double getAverageLife() {
        return averageLife;
    }

    public double getAverageMaxLife() {
        return averageMaxLife;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public double getAverageMaxEnergy() {
        return averageMaxEnergy;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getAverageVisionRange() {
        return averageVisionRange;
    }

    public double getAverageMatingEnergyNeeded() {
        return averageMatingEnergyNeeded;
    }

    public double getAverageBreedLength() {
        return averageBreedLength;
    }

    public double getAverageBreedProgressSpeed() {
        return averageBreedProgressSpeed;
    }

    public double getGenderRatio() {
        return genderRatio;
    }

    public double getPregnancyRatio() {
        return pregnancyRatio;
    }
}
