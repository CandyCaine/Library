/**
 * @author Rich Moore
 */

package me.rich.lib.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

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
	
	public Map<?, ?> getDesktopHints() {
		return (Map<?, ?>) (Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints"));
	}
	
	public BufferedImage setAlpha(BufferedImage src, int opacity) {
		BufferedImage image = this.toCompatibleImage(src);
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = image.getRGB(x, y);
				int alpha = (pixel & 0x0FF000000) >> 24;
				int red = (pixel & 0x00FF0000) >> 16;
				int green = (pixel & 0x0000FF00) >> 8;
				int blue = (pixel & 0x000000FF);
				
				alpha = opacity;
				
				int newRGB = ((alpha & 0x0FF) << 24 | (red & 0x0FF) << 16 | (green & 0x0FF) << 8 | (blue & 0x0FF));
				image.setRGB(x, y, newRGB);
			}
		}
		return this.toCompatibleImage(image);
	}
}
