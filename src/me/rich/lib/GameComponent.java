package me.rich.lib;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.rich.lib.display.Display;
import me.rich.lib.display.Window;
import me.rich.lib.input.Input;
import me.rich.lib.loop.GameLoop;
import me.rich.lib.loop.GameLoopListener;
import me.rich.lib.states.State;
import me.rich.lib.states.StateManager;
import me.rich.lib.time.GameTimer;

public abstract class GameComponent implements GameLoopListener {

	private int width;
	private int height;
	private String title;
	
	private GameLoop gameLoop;
	
	private Display display;
	private Window window;
	
	public GameComponent(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.window = new Window(width, height, title);
		this.display = new Display(width, height);
		this.display.addInput(new Input());
		
		this.window.addDisplay(display);
		this.display.requestFocus();
		
		this.gameLoop = new GameLoop(this);
		
		this.init();
		this.addStates(StateManager.getInstance());
		this.initStates();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void start() {
		this.gameLoop.start();
	}
	
	public void stop() {
		this.gameLoop.stop();
	}
	
	public abstract void init();
	
	public abstract void addStates(StateManager stateManager);
	
	public void addState(State state) {
		StateManager.getInstance().addState(state);
	}
	
	private void initStates() {
		if (!StateManager.getInstance().getStatesList().isEmpty()) {
			for (State state : StateManager.getInstance().getStatesList()) {
				state.init();
			}
		}
	}
	
	@Override
	public void update(double delta) {
		if (StateManager.getInstance().hasState()) {
			StateManager.getInstance().getCurrentState().update(delta);
		}
	}
	
	@Override
	public void render() {
		BufferStrategy buffer = this.display.getBufferStrategy();
		if (buffer == null) {
			this.display.createBufferStrategy(3);
			return;
		}
		
		do {
			do {
				Graphics g = buffer.getDrawGraphics();
				this.display.render(g);
				this.render(g);
				g.dispose();
			} while (buffer.contentsRestored());
			buffer.show();
		} while  (buffer.contentsLost());
	}
	
	private void render(Graphics g) {
		if (StateManager.getInstance().hasState()) {
			StateManager.getInstance().getCurrentState().render(g);
		}
	}
	
	public GameTimer getGameTime() {
		return this.gameLoop.getGameTime();
	}
	
	@Override
	public boolean hasFocus() {
		return display.hasFocus();
	}
	
	public void setNoFocusRendering(boolean noFocusRendering) {
		this.gameLoop.setNoFocusRendering(noFocusRendering);
	}
}
