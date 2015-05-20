package de.thomas.creatures.implementation.ai;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.WorldModel;

public abstract class CreatureAI {
	protected WorldModel worldModel;
	protected Creature creature;
	
	public abstract void init();
	public abstract void update();
	
	public Creature getCreature() {
		return creature;
	}
	
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
	
	public WorldModel getWorldModel() {
		return worldModel;
	}
	
	public void setWorldModel(WorldModel model) {
		this.worldModel = model;
	}
}
