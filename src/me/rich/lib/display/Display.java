/**
 * @author Rich Moore
 */

package me.rich.lib.display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import me.rich.lib.graphics.ImageUtil;
import me.rich.lib.input.Input;

public class Display extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private Input input;
	private BufferedImage bufferImage;

	public Display(int width, int height) {
		super(ImageUtil.getInstance().getGraphicsConfiguration());
		this.width = width;
		this.height = height;
		this.bufferImage = ImageUtil.getInstance().createImage(width, height, Transparency.OPAQUE);
		this.setIgnoreRepaint(true);
	}

	public void clear(Graphics g) {
		g.drawImage(bufferImage, 0, 0, width, height, null);
	}

	public void render(Graphics g) {
		clear(g);
	}

	public Input getInput() {
		if (!(input == null)) {
			return input;
		}
		return null;
	}

	public void addInput(Input input) {
		this.input = input;
		this.addKeyListener(input);
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
		this.addFocusListener(input);
	}

}
