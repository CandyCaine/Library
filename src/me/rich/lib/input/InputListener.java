package me.rich.lib.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputListener {

	public void onClick(MouseEvent event);
	
	public void onKeyTyped(KeyEvent event);
	
	public boolean isAcceptingInput();
	
}
