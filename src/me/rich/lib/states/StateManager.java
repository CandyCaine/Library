package me.rich.lib.states;

import java.util.ArrayList;

public class StateManager {
	private static StateManager instance = new StateManager();

	public static StateManager getInstance() {
		return instance;
	}

	private ArrayList<State> states;
	private State currentState;

	private StateManager() {
		this.states = new ArrayList<State>();
	}

	public ArrayList<State> getStatesList() {
		return states;
	}
	
	public void addState(State state) {
		if (this.states.isEmpty()) {
			this.currentState = state;
		}
		this.states.add(state);
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public State getState(int stateID) {
		if (!states.isEmpty()) {
			for (State state : states) {
				if (state.getStateID() == stateID) {
					return state;
				}
			}
		}
		return null;
	}

	public void setCurrentState(int stateID) {
		if (!states.isEmpty()) {
			for (State state : states) {
				if (state.getStateID() == stateID) {
					this.currentState = state;
				}
			}
		}
	}
	
	public boolean hasState() {
		if (!(this.currentState == null)) {
			return true;
		} else {
			return false;
		}
	}
}
