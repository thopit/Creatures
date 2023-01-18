package de.thomas.creatures.implementation.model;

import java.awt.*;

public class Food {
    private Point.Double position;
    private int value;

    public Food(Point.Double position, int value) {
        this.position = position;
        this.value = value;
    }

    public Point.Double getPosition() {
        return position;
    }

    public void setPosition(Point.Double position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
