/**
 * @author Rich Moore
 */

package me.rich.lib.entity;

public abstract class GenericComponent {

	public Entity entity;
	
	public Entity getEntityOwner() {
		return entity;
	}
	
	public void addEntityOwner(Entity entity) {
		this.entity = entity;
	}
	
	public abstract void update(double delta);
}
