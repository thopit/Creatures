package de.thomas.creatures.implementation.statistics;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.WorldModel;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final WorldModel worldModel;
    private final List<StatElement> statElements;
    private double lastTimeUpdated = 0;

    public Statistics(WorldModel worldModel) {
        this.worldModel = worldModel;
        statElements = new ArrayList<>();
    }

    public void update(double delta) {
        lastTimeUpdated += delta;

        if (lastTimeUpdated >= 1) {
            lastTimeUpdated = 0;

            addStatElement();
        }
    }

    private void addStatElement() {
        StatElement element = getStatElement();
        statElements.add(element);
    }

    private StatElement getStatElement() {
        int creatureAmount = worldModel.getCreatures().size();
        double averageLife;
        double averageMaxLife;
        double averageEnergy;
        double averageMaxEnergy;
        double averageSpeed;
        double averageVisionRange;
        double averageMatingEnergyNeeded;
        double averageBreedLength;
        double averageBreedProgressSpeed;
        double genderRatio;
        double pregnancyRatio;

        double totalLife = 0;
        double totalMaxLife = 0;
        double totalEnergy = 0;
        double totalMaxEnergy = 0;
        double totalAverageSpeed = 0;
        double totalAverageVisionRange = 0;
        double totalAverageMatingEnergyNeeded = 0;
        double totalAverageBreedLength = 0;
        double totalAverageBreedProgressSpeed = 0;
        double femaleAmount = 0;
        double pregnancyAmount = 0;

        for (Creature c : worldModel.getCreatures()) {
            totalLife += c.getLife();
            totalMaxLife += c.getMaxLife();
            totalEnergy += c.getEnergy();
            totalMaxEnergy += c.getMaxEnergy();
            totalAverageSpeed += c.getSpeed();
            totalAverageVisionRange += c.getVisionRange();
            totalAverageMatingEnergyNeeded += c.getMatingEnergyNeeded();
            totalAverageBreedLength += c.getBreedLength();
            totalAverageBreedProgressSpeed += c.getBreedProgressSpeed();

            if (c.getGender() == Gender.FEMALE) {
                femaleAmount += 1;
            }

            if (c.isPregnant()) {
                pregnancyAmount += 1;
            }
        }

        averageLife = totalLife / creatureAmount;
        averageMaxLife = totalMaxLife / creatureAmount;
        averageEnergy = totalEnergy / creatureAmount;
        averageMaxEnergy = totalMaxEnergy / creatureAmount;
        averageSpeed = totalAverageSpeed / creatureAmount;
        averageVisionRange = totalAverageVisionRange / creatureAmount;
        averageMatingEnergyNeeded = totalAverageMatingEnergyNeeded / creatureAmount;
        averageBreedLength = totalAverageBreedLength / creatureAmount;
        averageBreedProgressSpeed = totalAverageBreedProgressSpeed / creatureAmount;
        genderRatio = femaleAmount / creatureAmount;
        pregnancyRatio = pregnancyAmount / (genderRatio * creatureAmount);

        return new StatElement(
                creatureAmount,
                averageLife,
                averageMaxLife,
                averageEnergy,
                averageMaxEnergy,
                averageSpeed,
                averageVisionRange,
                averageMatingEnergyNeeded,
                averageBreedLength,
                averageBreedProgressSpeed,
                genderRatio,
                pregnancyRatio);
    }

    public List<StatElement> getStatElements() {
        return statElements;
    }

    public StatElement getTopStatElement() {
        return statElements.get(statElements.size() - 1);
    }
}
