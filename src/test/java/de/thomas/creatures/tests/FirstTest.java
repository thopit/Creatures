package de.thomas.creatures.tests;


import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.WorldModel;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO Add tests!
public class FirstTest {

    @Test
    public void testWayPoints() {
        int width = 1000;
        int height = 500;
        int foodCreationRate = 50;

        WorldModel model = new WorldModel(width, height, foodCreationRate);
        Creature creature = new Creature(new Point2D.Double(100, 100), Gender.MALE);

        BasicAI basicAI = new BasicAI();
        basicAI.setCreature(creature);
        basicAI.setWorldModel(model);

        basicAI.init();

        for (Point2D.Double p : basicAI.getWayPoints()) {
            assertTrue(p.x <= width);
            assertTrue(p.x >= 0);
            assertTrue(p.y <= height);
            assertTrue(p.y >= 0);
        }
    }
}
