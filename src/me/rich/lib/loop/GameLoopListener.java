package me.rich.lib.loop;

public interface GameLoopListener {

	public void update(double delta);

	public void render();
	
	public boolean hasFocus();

}
