package me.rich.lib.states;

import java.awt.Graphics;

public abstract class State {

	public int stateID;
	
	public State(int stateID) {
		this.stateID = stateID;
	}
	
	public int getStateID() {
		return this.stateID;
	}
	
	public abstract void init();
	
	public abstract void update(final double delta);
	
	public abstract void render(Graphics g);
}
