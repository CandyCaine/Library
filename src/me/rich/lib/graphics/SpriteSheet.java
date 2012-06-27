package me.rich.lib.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private int cellSize;
	private BufferedImage image;
	
	public SpriteSheet(String path, int cellSize, int color) {
		this.cellSize = cellSize;
		this.image = ImageUtil.getInstance().stripColor(ImageUtil.getInstance().loadImage(path), color);
	}
	
	public BufferedImage getSpriteSheetImage() {
		return this.image;
	}
	
	public BufferedImage getImage(int x, int y, int width, int height) {
		return ImageUtil.getInstance().toCompatibleImage(this.image.getSubimage(x, y, width, height));
	}
	
	public BufferedImage getCell(int cell) {
		int sheetWidth = this.image.getWidth() / this.cellSize;
		int cellX = cellSize * (cell % sheetWidth);
		int cellY = cellSize * (cell / sheetWidth);
		
		return ImageUtil.getInstance().toCompatibleImage(this.image.getSubimage(cellX, cellY, this.cellSize, this.cellSize));
	}
}
