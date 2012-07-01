/**
 * @author Rich Moore
 */

package me.rich.lib.entity;

import java.awt.Graphics;

public abstract class GenericRenderComponent {

	public Entity entity;
	
	public Entity getEntityOwner() {
		return entity;
	}
	
	public void addEntityOwner(Entity entity) {
		this.entity = entity;
	}
	
	public abstract void update(double delta);
	
	public abstract void render(Graphics g);
}
