package de.thomas.creatures.implementation.view;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.Food;
import de.thomas.creatures.implementation.model.WorldModel;

import javax.swing.*;
import java.awt.*;

public class WorldView extends JPanel {
    private static final long serialVersionUID = 5628493124295876371L;
    public static int CREATURE_SIZE = 12;
    public static int FOOD_SIZE = 8;
    public static boolean DEBUG_MODE = true;

    private transient WorldModel world;

    private int zoomFactor;
    private int offsetX;
    private int offsetY;

    public WorldView(WorldModel world) {
        this.world = world;
        zoomFactor = 1;

        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        //Draw food
        g.setColor(new Color(116, 195, 101));
        for (Food food : world.getFoods()) {
            g.fillOval((int) (food.getPosition().x / zoomFactor + (double) offsetX / zoomFactor),
                    (int) (food.getPosition().y / zoomFactor + (double) offsetY / zoomFactor),
                    FOOD_SIZE / zoomFactor,
                    FOOD_SIZE / zoomFactor);
        }

        //Draw creatures
        for (Creature creature : world.getCreatures()) {
            if (creature.getGender() == Gender.MALE) {
                g.setColor(new Color(0, 0, 255));
            } else {
                g.setColor(new Color(255, 0, 0));
            }

            g.fillOval((int) (creature.getPosition().x / zoomFactor + (double) offsetX / zoomFactor),
                    (int) (creature.getPosition().y / zoomFactor + (double) offsetY / zoomFactor),
                    CREATURE_SIZE / zoomFactor,
                    CREATURE_SIZE / zoomFactor);
        }

        if (DEBUG_MODE) {
            for (Creature creature : world.getCreatures()) {
                g.setColor(Color.black);
                String displayString = ((int) ((creature.getLife() / creature.getMaxLife()) * 100)) + " | "
                        + ((int) creature.getEnergy());

                g.drawString(displayString,
                        (int) (creature.getPosition().x / zoomFactor + (double) offsetX / zoomFactor),
                        (int) (creature.getPosition().y / zoomFactor + (double) offsetY / zoomFactor));
            }
        }
    }

    public int getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(int zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setWorld(WorldModel worldModel) {
        this.world = worldModel;
    }
}
