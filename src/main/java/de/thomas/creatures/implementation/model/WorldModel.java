package de.thomas.creatures.implementation.model;

import java.util.ArrayList;
import java.util.List;

public class WorldModel {
    //TODO add better access methods for creatures and food (grid based etc.)
    public static int maxFoodEnergy = 100;
    public static int maxFoodAmount = 500;
    public static double baseEnergyDepletionRate = 1;
    public static double speedEnergyDepletionFactor = 0.5;
    public static double mutationRate = 0.1;
    public static double speedFactor = 1;

    private final List<Creature> creatures;
    private final List<Food> foods;
    private double width;
    private double height;
    private int foodCreationRate;

    public WorldModel(int width, int height, int foodCreationRate) {
        creatures = new ArrayList<>();
        foods = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.foodCreationRate = foodCreationRate;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getFoodCreationRate() {
        return foodCreationRate;
    }

    public void setFoodCreationRate(int foodCreationRate) {
        this.foodCreationRate = foodCreationRate;
    }
}
