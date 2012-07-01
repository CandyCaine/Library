/**
 * @author Rich Moore
 */

package me.rich.lib.graphics.sprite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpriteRenderer {

	private ArrayList<Sprite> sprites;
	
	public SpriteRenderer() {
		sprites = new ArrayList<Sprite>();
	}
	
	private final Comparator<Sprite> Z_ORDER = new Comparator<Sprite>() {
		@Override
		public int compare(Sprite s1, Sprite s2) {
			return s1.getZ() + s2.getZ();
		}
	};
	
	public Comparator<Sprite> getZOrder() {
		return Z_ORDER;
	}
	
	public void sortByZ() {
		Collections.sort(sprites, this.getZOrder());
	}
	
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}
	
	public void renderSprites(Graphics g) {
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
	}
	
	public void renderSpritesDistance(Graphics g) {
		for (Sprite sprite : sprites) {
			int x = (int) sprite.getX();
			int y = (int) sprite.getY();
			int z = sprite.getZ();
			int width = sprite.getWidth();
			int height = sprite.getHeight();
			
			//Needs a proper algorithm for calculating the scale.
			width -= z;
			height -= z;
			
			g.drawImage(sprite.getImage(), x, y, width, height, null);
		}
	}
}