package de.thomas.creatures.implementation.statistics;

import java.util.Vector;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.WorldModel;
import de.thomas.creatures.implementation.model.Creature.Gender;

public class Statistics {
	private WorldModel worldModel;
	private Vector<StatElement> statElements;
	private double lastTimeUpdated = 0;

	public Statistics(WorldModel worldModel) {
		this.worldModel = worldModel;
		statElements = new Vector<StatElement>();
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
		double averageLife = 0;
		double averageMaxLife = 0;
		double averageEnergy = 0;
		double averageMaxEnergy = 0;
		double averageSpeed = 0;
		double averageVisionRange = 0;
		double averageMatingEnergyNeeded = 0;
		double averageBreedLength = 0;
		double averageBreedProgressSpeed = 0;
		double genderRatio = 0;
		double pregnancyRatio = 0;

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

		StatElement element = new StatElement(
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

		return element;
	}

	public Vector<StatElement> getStatElements() {
		return statElements;
	}

	public StatElement getTopStatElement() {
		return statElements.lastElement();
	}
}
