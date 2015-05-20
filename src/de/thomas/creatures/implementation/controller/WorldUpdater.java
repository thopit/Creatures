package de.thomas.creatures.implementation.controller;

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.ai.CreatureAI;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.Food;
import de.thomas.creatures.implementation.model.WorldModel;

public class WorldUpdater {
	private WorldModel worldModel;
	private WorldController worldController;
	private Vector<Creature> newBornList = new Vector<Creature>();
	
	public WorldUpdater(WorldModel worldModel, WorldController worldController) {
		this.worldModel = worldModel;
		this.worldController = worldController;
	}
	
	public void updateWorld(double delta) {	
		Iterator<Creature> creatureIterator = worldModel.getCreatures().iterator();

		while (creatureIterator.hasNext()) {
			Creature creature = creatureIterator.next();

			handleMoving(delta, creature);
			handleMating(delta, creature);
			handleFoodIntake(delta, creature);

			if (creature.isPregnant()) {
				handlePregnancy(creature, delta);
			}

			handleEnergyDepletion(delta, creatureIterator, creature);
			handleLifeDepletion(delta, creatureIterator, creature);
		}

		Iterator<Creature> newBornIterator = newBornList.iterator();
		while (newBornIterator.hasNext()) {
			Creature newBorn = newBornIterator.next();
			worldController.addCreature(newBorn);
			newBornIterator.remove();
		}

		handleFoodCreation();
	}

	private void handleMoving(double delta, Creature creature) {
		//TODO Maybe only compute stuff if target changes, else let speed be the same

		Point.Double target = creature.getTarget();
		Point.Double position = creature.getPosition();

		//Move nearer to target
		if (target != null) {
			if (target.distance(position) > (creature.getSpeed() * delta * WorldModel.speedFactor * 1.25)) {
				double x = target.x - position.x;
				double y = target.y - position.y;
				double speed = creature.getSpeed() * delta * WorldModel.speedFactor;

				double alpha = speed / Math.sqrt(x*x + y*y);
				
				double speedX = alpha * x;
				double speedY = alpha * y;

				creature.getPosition().x += speedX;
				creature.getPosition().y += speedY;
			}
			else {
				creature.setPosition(new Point.Double(creature.getTarget().x, creature.getTarget().y));
				creature.setTarget(null);
			}
		}
	}

	private void handleMating(double delta, Creature creature) {
		Iterator<Creature> secondIterator = worldModel.getCreatures().iterator();
		while (secondIterator.hasNext()) {
			Creature secondCreature = secondIterator.next();

			if (creature.getPosition().distance(secondCreature.getPosition()) < (creature.getSpeed() * delta * WorldModel.speedFactor * 1.25) && 
					creature.getEnergy() > creature.getMatingEnergyNeeded() && 
					secondCreature.getEnergy() > secondCreature.getMatingEnergyNeeded() &&
					creature.getGender() != secondCreature.getGender() &&
					! creature.isPregnant() && ! secondCreature.isPregnant()) {

				Creature father;
				Creature mother;

				if (creature.getGender() == Gender.FEMALE) {
					father = secondCreature;
					mother = creature;
				}
				else {
					father = creature;
					mother = secondCreature;
				}

				mother.setPregnant(true);
				Creature fetus = createFetus(father, mother);
				mother.setFetus(fetus);
			}
		}
	}

	private void handleFoodIntake(double delta, Creature creature) {
		Iterator<Food> foodIterator = worldModel.getFoods().iterator();

		//Remove food and add energy to creature if it is close enough
		while (foodIterator.hasNext()) {
			Food f = foodIterator.next();

			if (creature.getPosition().distance(f.getPosition()) < (creature.getSpeed() * delta * 1.25 * WorldModel.speedFactor)) {
				creature.setEnergy(creature.getEnergy() + f.getValue());
				foodIterator.remove();

				if (creature.getEnergy() > creature.getMaxEnergy())
					creature.setEnergy(creature.getMaxEnergy());
			}
		}
	}

	private void handleEnergyDepletion(double delta, Iterator<Creature> creatureIterator, Creature creature) {
		double energyDepletion = WorldModel.baseEnergyDepletionRate;

		if (creature.getTarget() != null) {
			energyDepletion += creature.getSpeed();
		}

		if (creature.isPregnant()) {
			energyDepletion += creature.getBreedProgressSpeed();
		}

		creature.setEnergy(creature.getEnergy() - (energyDepletion * delta * WorldModel.speedFactor));

		if (creature.getEnergy() <= 1) {
			creatureIterator.remove();
		}
	}

	private void handleLifeDepletion(double delta, Iterator<Creature> creatureIterator, Creature creature) {
		creature.setLife(creature.getLife() + (1 * delta * WorldModel.speedFactor));

		if (creature.getLife() >= creature.getMaxLife()) {
			creatureIterator.remove();
		}
	}

	private void handleFoodCreation() {
		for (int i = 0; i < WorldModel.speedFactor; i++) {
			if (worldModel.getFoods().size() < WorldModel.maxFoodAmount &&
					(int) (Math.random() * 100) < worldModel.getFoodCreationRate()) {
				double xPos = worldModel.getWidth() * Math.random();
				double yPos = worldModel.getHeight() * Math.random();

				Food food = new Food(new Point.Double(xPos, yPos), (int) (Math.random() * WorldModel.maxFoodEnergy));
				worldModel.addFood(food);
			}
		}
	}

	private void handlePregnancy(Creature creature, double delta) {
		if (creature.getBreedTime() > 1) {
			creature.setBreedTime(creature.getBreedTime() - (creature.getBreedProgressSpeed() * delta * WorldModel.speedFactor));
		}
		else {
			creature.setBreedTime(creature.getBreedLength());
			creature.setPregnant(false);
			Point.Double motherPosition = creature.getPosition();

			Creature newBorn = creature.getFetus();
			newBorn.setPosition(new Point.Double(motherPosition.x, motherPosition.y));

			newBornList.add(newBorn);
			creature.setFetus(null);
		}
	}

	private Creature createFetus(Creature father, Creature mother) {
		double energy = mother.getBreedLength();
		double maxEnergy = ((father.getMaxEnergy() + mother.getMaxEnergy()) / 2) * mutationFactor(WorldModel.mutationRate);
		double maxLife = ((father.getMaxLife() + mother.getMaxLife()) / 2) * mutationFactor(WorldModel.mutationRate);
		//Position will be added later
		Point.Double position = null;
		double speed = ((father.getSpeed() + mother.getSpeed()) / 2) * mutationFactor(WorldModel.mutationRate);
		double visionRange = ((father.getVisionRange() + mother.getVisionRange()) / 2) * mutationFactor(WorldModel.mutationRate);
		Gender gender;
		if (Math.random() >= 0.5)
			gender = Gender.MALE;
		else
			gender = Gender.FEMALE;
		CreatureAI ai = new BasicAI();
		double matingEnergyNeeded = ((father.getMatingEnergyNeeded() + mother.getMatingEnergyNeeded()) / 2) * mutationFactor(WorldModel.mutationRate);
		double breedLength = ((father.getBreedLength() + mother.getBreedLength()) / 2) * mutationFactor(WorldModel.mutationRate);
		double breedProgressSpeed = ((father.getBreedProgressSpeed() + mother.getBreedProgressSpeed()) / 2) * mutationFactor(WorldModel.mutationRate);

		Creature fetus = new Creature(energy, 
				maxEnergy, 
				maxLife, 
				position, 
				speed, 
				visionRange, 
				gender, 
				ai, 
				matingEnergyNeeded, 
				breedLength, 
				breedProgressSpeed);

		return fetus;
	}

	private double mutationFactor(double mutationRate) {
		double mutationDelta = Math.random() * mutationRate;

		double plusOrMinus = Math.random();

		if (plusOrMinus > 0.5) {
			return 1 + mutationDelta;
		}
		else {
			return 1 - mutationDelta;
		}
	}
}
