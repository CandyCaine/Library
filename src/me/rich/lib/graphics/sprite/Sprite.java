/**
 * @author Rich Moore
 */

package me.rich.lib.graphics.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {

	private float x;
	private float y;
	private int z;
	
	private int width;
	private int height;
	
	private BufferedImage image;
	
	public Sprite(float x, float y, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, Math.round(x), Math.round(y), width, height, null);
	}
	
	public void render(Graphics g, float x, float y) {
		g.drawImage(image, Math.round(x), Math.round(y), width, height, null);
	}
}
