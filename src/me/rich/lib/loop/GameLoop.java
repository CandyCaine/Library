/**
 * @author Rich Moore
 */

package me.rich.lib.loop;

import me.rich.lib.time.GameTimer;
import me.rich.lib.time.TimeKeeper;

public class GameLoop implements Runnable {
	private Thread thread;
	private boolean running = false;

	private GameTimer gameTime;
	private GameLoopListener listener;

	private boolean noFocusRendering = false;

	public GameLoop(GameLoopListener listener) {
		this.listener = listener;
		this.gameTime = new GameTimer("GameTime");
		TimeKeeper.getInstance().addTimer(gameTime);
	}

	public void start() {
		if (this.running) {
			return;
		} else {
			this.running = true;
			this.thread = new Thread(this);
			this.thread.start();
		}
	}

	public void stop() {
		if (!this.running) {
			return;
		} else {
			this.running = false;
			try {
				this.thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		long lastLoopTime = System.nanoTime();
		final long optimumTime = 1000000000 / 60;
		long lastFpsTime = 0;
		long fps = 0;

		while (running) {

			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) optimumTime);

			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				this.updateTime();
				if (this.gameTime.getSeconds() > 86400) {
					this.gameTime.reset();
				}

				System.out.println("FPS: " + fps + " Time: " + gameTime.getTime());
				fps = 0;
				lastFpsTime = 0;
			}

			this.listener.update(delta);

			if (this.noFocusRendering) {
				this.listener.render();
			} else if (this.listener.hasFocus() && !this.noFocusRendering) {
				this.listener.render();
			}

			long sleepTime = (lastLoopTime - System.nanoTime()) / 1000000 + 10;
			if (sleepTime >= 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void updateTime() {
		TimeKeeper.getInstance().update();
	}

	public GameTimer getGameTime() {
		return gameTime;
	}
	
	public boolean isNoFocusRendering() {
		return this.noFocusRendering;
	}

	public void setNoFocusRendering(boolean noFocusRendering) {
		this.noFocusRendering = noFocusRendering;
	}
}
