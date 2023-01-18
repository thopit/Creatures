package de.thomas.creatures.implementation.ai;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Food;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasicAI extends CreatureAI {
    private final List<Point.Double> wayPoints;

    public BasicAI() {
        wayPoints = new ArrayList<>();
    }

    @Override
    public void init() {
        initWayPoints();
    }

    private void initWayPoints() {
        int WAY_POINT_NUMBER = 10;
        int wayPointNumberX = WAY_POINT_NUMBER;
        int wayPointNumberY = (int) (WAY_POINT_NUMBER * (worldModel.getHeight() / worldModel.getWidth()));

        double deviationX = worldModel.getWidth() / wayPointNumberX;
        double deviationY = worldModel.getHeight() / wayPointNumberY;

        double maxRandom = deviationX / 2;

        for (int y = 0; y < wayPointNumberY; y++) {
            for (int x = 0; x < wayPointNumberX; x++) {
                Point.Double point = new Point.Double(x * deviationX + Math.random() * maxRandom,
                        y * deviationY + Math.random() * maxRandom);

                wayPoints.add(point);
            }
        }
    }

    @Override
    public void update() {
        //Move random
        if (creature.getTarget() == null) {
            Point.Double point = wayPoints.get((int) (Math.random() * wayPoints.size()));
            creature.setTarget(point);
        }

        Food nearestFood = getNearestFood();

        //Goto food
        if (nearestFood != null && creature.getEnergy() + nearestFood.getValue() <= creature.getMaxEnergy()) {
            creature.setTarget(nearestFood.getPosition());
        }

        //Goto mate
        Creature nearestMate = getNearestMate();

        if (nearestMate != null) {
            creature.setTarget(nearestMate.getPosition());
        }
    }

    private Food getNearestFood() {
        Food nearestFood = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Food f : getWorldModel().getFoods()) {
            double distance = f.getPosition().distance(getCreature().getPosition());

            if (distance < getCreature().getVisionRange() && distance < nearestDistance) {
                nearestFood = f;
                nearestDistance = distance;
            }
        }

        return nearestFood;
    }

    private Creature getNearestMate() {
        Creature nearestMate = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Creature c : getWorldModel().getCreatures()) {
            double distance = c.getPosition().distance(getCreature().getPosition());

            if (getCreature().getGender() != c.getGender()
                    && distance < getCreature().getVisionRange()
                    && distance < nearestDistance
                    && getCreature().getEnergy() > getCreature().getMatingEnergyNeeded()
                    && c.getEnergy() > c.getMatingEnergyNeeded() &&
                    !getCreature().isPregnant() && !c.isPregnant()) {

                nearestMate = c;
                nearestDistance = distance;
            }
        }

        return nearestMate;
    }

    public List<Point.Double> getWayPoints() {
        return wayPoints;
    }
}
