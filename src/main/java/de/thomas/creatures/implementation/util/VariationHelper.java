package de.thomas.creatures.implementation.util;

public class VariationHelper {
    public static double mutationFactor(double mutationRate) {
        double mutationDelta = Math.random() * mutationRate;

        if (Math.random() > 0.5) {
            return 1 + mutationDelta;
        } else {
            return 1 - mutationDelta;
        }
    }
}
