/**
 * @author Rich Moore
 */

package me.rich.lib.entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class Entity {

	private float x;
	private float y;
	
	private ArrayList<GenericComponent> components;
	private GenericRenderComponent renderComponent;
	
	public Entity() {
		this.components = new ArrayList<GenericComponent>();
	}
	
	public Entity(float x, float y) {
		this.components = new ArrayList<GenericComponent>();
		this.setX(x);
		this.setY(y);
	}

	public Entity(float x, float y, GenericComponent[] components, GenericRenderComponent renderComponent) {
		this.components = new ArrayList<GenericComponent>();
		this.setX(x);
		this.setY(y);
		for (GenericComponent component : components) {
			this.addComponent(component);
		}
		this.addRenderComponent(renderComponent);
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
	
	public void addComponent(GenericComponent component) {
		component.addEntityOwner(this);
		this.components.add(component);
	}
	
	public void addRenderComponent(GenericRenderComponent renderComponent) {
		renderComponent.addEntityOwner(this);
		this.renderComponent = renderComponent;
	}
	
	public void update(double delta) {
		for (GenericComponent component : this.components) {
			component.update(delta);
		}
		
		if (!(this.renderComponent == null)) {
			this.renderComponent.update(delta);
		}
	}
	
	public void render(Graphics g) {
		if (!(this.renderComponent == null)) {
			this.renderComponent.render(g);
		}
	}
}
