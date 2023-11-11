package de.thomas.creatures.implementation.model;

import de.thomas.creatures.implementation.ai.CreatureAI;
import de.thomas.creatures.implementation.ai.DoNothingAI;

import java.awt.geom.Point2D;

public class Creature {
    private double life;

    private double maxLife;
    private double energy;
    private double maxEnergy;
    private Point2D.Double position;
    private double speed;
    private double visionRange;
    private Gender gender;
    private CreatureAI ai;
    private double matingEnergyNeeded;
    private double breedLength;
    private double breedProgressSpeed;
    private double breedTime;
    private boolean pregnant;
    private Creature fetus;
    private Point2D.Double target;

    //For test purposes
    public Creature(Point2D.Double position, Gender gender) {
        this(
                1000,
                1000,
                500,
                position,
                24,
                100,
                gender,
                new DoNothingAI(),
                100,
                200,
                5);
    }

    public Creature(
            double energy,
            double maxEnergy,
            double maxLife,
            Point2D.Double position,
            double speed,
            double visionRange,
            Gender gender,
            CreatureAI ai,
            double matingEnergyNeeded,
            double breedLength,
            double breedProgressSpeed) {

        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.setMaxLife(maxLife);
        this.position = position;
        this.speed = speed;
        this.visionRange = visionRange;
        this.gender = gender;
        this.ai = ai;
        this.matingEnergyNeeded = matingEnergyNeeded;
        this.breedLength = breedLength;
        this.breedProgressSpeed = breedProgressSpeed;

        this.target = null;
        this.pregnant = false;
        this.breedTime = breedLength;
        this.life = 0;
    }

    public void update() {
        ai.update();
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Point2D.Double getTarget() {
        return target;
    }

    public void setTarget(Point2D.Double target) {
        this.target = target;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public CreatureAI getAi() {
        return ai;
    }

    public void setAi(CreatureAI ai) {
        this.ai = ai;
    }

    public double getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(double visionRange) {
        this.visionRange = visionRange;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public double getMatingEnergyNeeded() {
        return matingEnergyNeeded;
    }

    public void setMatingEnergyNeeded(double matingEnergyNeeded) {
        this.matingEnergyNeeded = matingEnergyNeeded;
    }

    public double getBreedTime() {
        return breedTime;
    }

    public void setBreedTime(double breedTime) {
        this.breedTime = breedTime;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public double getBreedLength() {
        return breedLength;
    }

    public void setBreedLength(double breedLength) {
        this.breedLength = breedLength;
    }

    public double getBreedProgressSpeed() {
        return breedProgressSpeed;
    }

    public void setBreedProgressSpeed(double breedProgressSpeed) {
        this.breedProgressSpeed = breedProgressSpeed;
    }

    public double getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    public Creature getFetus() {
        return fetus;
    }

    public void setFetus(Creature fetus) {
        this.fetus = fetus;
    }

    public enum Gender {MALE, FEMALE}
}
