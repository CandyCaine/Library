package me.rich.lib.time;

import java.util.ArrayList;

public class TimeKeeper {
	private static TimeKeeper instance = new TimeKeeper();
	
	public static TimeKeeper getInstance() {
		return instance;
	}
	
	private ArrayList<GameTimer> timers;
	
	private TimeKeeper() {
		this.timers = new ArrayList<GameTimer>();
	}
	
	public void addTimer(GameTimer timer) {
		this.timers.add(timer);
	}
	
	public void update() {
		for (GameTimer timer : timers) {
			timer.update();
		}
	}
}
