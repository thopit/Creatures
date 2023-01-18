package de.thomas.creatures.implementation.model;

import java.awt.geom.Point2D;

public class Food {
    private Point2D.Double position;
    private int value;

    public Food(Point2D.Double position, int value) {
        this.position = position;
        this.value = value;
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
