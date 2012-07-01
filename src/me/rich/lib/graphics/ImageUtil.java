/**
 * @author Rich Moore
 */

package me.rich.lib.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	private static ImageUtil instance = new ImageUtil();

	public static ImageUtil getInstance() {
		return instance;
	}

	private ImageUtil() {
	}

	public GraphicsConfiguration getGraphicsConfiguration() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	}

	public BufferedImage createImage(int width, int height, int transparency) {
		return this.getGraphicsConfiguration().createCompatibleImage(width, height, transparency);
	}

	public BufferedImage toCompatibleImage(BufferedImage src) {
		BufferedImage image = createImage(src.getWidth(), src.getHeight(), Transparency.TRANSLUCENT);
		Graphics g = image.getGraphics();
		g.drawImage(src, 0, 0, src.getWidth(), src.getHeight(), null);
		g.dispose();
		return image;
	}

	public BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageUtil.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.toCompatibleImage(image);
	}

	public BufferedImage stripColor(BufferedImage src, int color) {
		for (int y = 0; y < src.getHeight(); y++) {
			for (int x = 0; x < src.getWidth(); x++) {
				if (src.getRGB(x, y) == color) {
					src.setRGB(x, y, 0x00000000);
				}
			}
		}
		return this.toCompatibleImage(src);
	}

	public BufferedImage rotateImage(BufferedImage src, double angle) {
		int width = src.getWidth();
		int height = src.getHeight();
		BufferedImage image = this.createImage(width, height, Transparency.TRANSLUCENT);
		Graphics2D g = image.createGraphics();
		g.rotate(Math.toRadians(angle), width / 2, height / 2);
		g.drawImage(src, null, 0, 0);
		g.dispose();
		return this.toCompatibleImage(image);
	}
}
