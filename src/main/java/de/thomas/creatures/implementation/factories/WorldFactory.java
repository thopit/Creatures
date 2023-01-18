package de.thomas.creatures.implementation.factories;

import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.ai.CreatureAI;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.Food;
import de.thomas.creatures.implementation.model.WorldModel;

import java.awt.*;

public class WorldFactory {

    public static WorldModel createTestWorld() {
        WorldModel world = new WorldModel(1000, 800, 50);
        Creature[] creatures = {new Creature(new Point.Double(100, 100), Gender.MALE),
                new Creature(new Point.Double(200, 100), Gender.FEMALE)};
        //Creature[] creatures = {new Creature(new Point.Double(100, 100), Gender.MALE)};

        CreatureAI ai = new BasicAI();

        creatures[0].setAi(ai);

        ai.setWorldModel(world);
        ai.setCreature(creatures[0]);
        ai.init();


        Food[] foods = {new Food(new Point.Double(150, 150), 100),
                new Food(new Point.Double(230, 80), 100)
        };

        for (Creature c : creatures) {
            world.addCreature(c);
        }

        for (Food f : foods) {
            world.addFood(f);
        }

        return world;
    }

    public static WorldModel createEmptyWorld(int width, int height, int foodRate) {
        WorldModel world = new WorldModel(width, height, foodRate);
        return world;
    }

    public static WorldModel createBasicWorld(int width, int height, int creatureAmount, int foodRate) {
        WorldModel world = new WorldModel(width, height, foodRate);
        Creature[] creatures = new Creature[creatureAmount];

        for (int i = 0; i < creatureAmount; i++) {
            double posX = width * Math.random();
            double posY = height * Math.random();
            Gender gender;

            if (Math.random() >= 0.5) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }

            creatures[i] = new Creature(new Point.Double(posX, posY), gender);

            CreatureAI ai = new BasicAI();
            creatures[i].setAi(ai);

            ai.setCreature(creatures[i]);
            ai.setWorldModel(world);
            ai.init();

            world.addCreature(creatures[i]);
        }

        Food[] foods = new Food[creatureAmount * 10];
        for (int i = 0; i < creatureAmount * 10; i++) {
            double posX = width * Math.random();
            double posY = height * Math.random();
            foods[i] = new Food(new Point.Double(posX, posY), (int) (Math.random() * 100));

            world.addFood(foods[i]);
        }

        return world;
    }
}
