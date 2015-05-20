package de.thomas.creatures.tests;

import java.awt.Point;

import org.junit.Test;

import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.model.WorldModel;

//TODO Add tests!
public class FirstTest {
	
	@Test
	public void test() {
		WorldModel model = new WorldModel(1000, 500, 50);
		Creature creature = new Creature(new Point.Double(100, 100), Gender.MALE);
		
		BasicAI basicAI = new BasicAI();
		basicAI.setCreature(creature);
		basicAI.setWorldModel(model);
		
		basicAI.init();
		
		System.out.println(basicAI.getWayPoints().size());
		
		for (Point.Double p : basicAI.getWayPoints()) {
			System.out.println(p);
		}
		
	}
}
