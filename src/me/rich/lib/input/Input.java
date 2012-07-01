/**
 * @author Rich Moore
 */

package me.rich.lib.input;

import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, FocusListener {

	private static boolean[] keys;
	private static boolean leftMouseButton;
	private static boolean middleMouseButton;
	private static boolean rightMouseButton;
	private static boolean focus;
	private static Point mousePos;
	
	public static enum MouseButton {
		LEFT,
		MIDDLE,
		RIGHT,
		ANY;
	}
	
	public Input() {
		keys = new boolean[1000];
		mousePos = new Point(0, 0);
	}
	
	public static boolean isKeyDown(int key) {
		if (key >= 0 && key <= keys.length) {
			return keys[key];
		} else {
			return false;
		}
	}
	
	public static boolean isMouseDown(MouseButton button) {
		if (button == MouseButton.LEFT) {
			return leftMouseButton;
		} else if (button == MouseButton.LEFT) {
			return middleMouseButton;
		} else if (button == MouseButton.LEFT) {
			return rightMouseButton;
		} else if (button == MouseButton.ANY) {
			if (leftMouseButton || middleMouseButton || rightMouseButton) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasFocus() {
		return focus;
	}
	
	public static Point getMousePos() {
		return mousePos;
	}
	
	public static int getMouseX() {
		return mousePos.x;
	}
	
	public static int getMouseY() {
		return mousePos.y;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		focus = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		focus = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getPoint();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftMouseButton = true;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			middleMouseButton = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightMouseButton = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftMouseButton = false;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			middleMouseButton = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightMouseButton = false;
		}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mousePos = e.getPoint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mousePos = e.getPoint();		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() <= keys.length) {
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() <= keys.length) {
			keys[e.getKeyCode()] = false;
		}		
	}

}
