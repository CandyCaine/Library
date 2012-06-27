package me.rich.lib.display;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private String title;
	
	private Display display;
	
	public Window(int width, int height, String title) {
		super(title);
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIgnoreRepaint(true);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public void addDisplay(Display display) {
		this.display = display;
		this.add(display);
		this.pack();
		this.setLocationRelativeTo(null);
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
	
	
}
