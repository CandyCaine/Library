/**
 * @author Rich Moore
 */

package me.rich.lib.time;

public class GameTimer {

	private String name;
	private long seconds;
	
	private boolean limited;
	private long timeLimit;
	private boolean limitReached = false;
	
	private TimerListener listener;
	
	public GameTimer() {
		this.limited = false;
		this.limitReached = false;
	}

	public GameTimer(String name) {
		this.name = name;
		this.limited = false;
		this.limitReached = false;
	}
	
	public GameTimer(long limit, TimerListener listener) {
		this.limited = true;
		this.timeLimit = limit;
		this.listener = listener;
	}
	
	public GameTimer(String name, long limit, TimerListener listener) {
		this.name = name;
		this.limited = true;
		this.timeLimit = limit;
		this.listener = listener;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void update() {
		this.seconds++;
		if (this.limited && !this.hasReachedLimit()) {
			if (this.seconds > this.timeLimit) {
				if (!(listener == null)) {
					this.listener.onTimerFinish();
				}
				this.limitReached = true;
			}
		}
	}
	
	public String getTime() {
		String h = null, m = null, s = null;
		double hours = Math.floor(seconds / (60 * 60));
		long div_for_min = seconds % (60 * 60);
		double minutes = Math.floor(div_for_min / 60);
		long div_for_sec = div_for_min % 60;
		double sec = Math.ceil(div_for_sec);
		
		if (hours < 10) { h = "0" + (int) hours; } else { h = "" + (int) hours; }
		if (minutes < 10) { m = "0" + (int) minutes; } else { m = "" + (int) minutes; }
		if (sec < 10) { s = "0" + (int) sec; } else { s = "" + (int) sec; }
		
		return h + ":" + m + ":" + s;
	}
	
	public long getSeconds() {
		return seconds;
	}
	
	public long getMinutes() {
		long div_for_min = seconds % (60 * 60);
		return (long) Math.floor(div_for_min / 60);
	}
	
	public long getHours() {
		return (long) Math.floor(seconds / (60 * 60));
	}
	
	public void reset() {
		seconds = 0L;
	}
	
	public boolean hasReachedLimit() {
		return limitReached;
	}
	
	public long getLimit() {
		if (limited) {
			return timeLimit;
		} else {
			return -1;
		}
	}
	
}
