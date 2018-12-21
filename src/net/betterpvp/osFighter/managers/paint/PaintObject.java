package net.betterpvp.osFighter.managers.paint;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.betterpvp.osFighter.managers.Paint;

public abstract class PaintObject {
	
	private int x, y, width, height;
	private boolean enabled;
	private Rectangle rectangle;
	private String ID;
	
	public PaintObject(String ID, int x, int y, int width, int height){
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(x, y, width, height);
		this.enabled = true;
	}
	
	public PaintObject(String ID, boolean enabled, int x, int y, int width, int height){
		this.ID = ID;
		this.enabled = enabled;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(x, y, width, height);

	}
	
	public String getID(){
		return this.ID;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
	
	public boolean isEnabled(){
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update(Paint paint);

}
