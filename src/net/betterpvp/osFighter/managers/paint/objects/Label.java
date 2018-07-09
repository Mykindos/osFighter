package net.betterpvp.osFighter.managers.paint.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.betterpvp.osFighter.managers.Paint;
import net.betterpvp.osFighter.managers.paint.PaintObject;



public class Label extends PaintObject{
	
	private String text;
	private Color colour;
	private Font font;
	private Rectangle rec;

	public Label(String ID, int x, int y, String text, Color colour, Font font) {
		super(ID, x, y, 1, 1);
		this.text = text;
		this.colour = colour;
		this.font = font;
	
	}
	
	public Label(String ID, int x, int y, String text) {
		super(ID, x, y, 1, 1);
		this.text = text;
		this.colour = Color.WHITE;
		this.font = new Font("Calibri", Font.BOLD, 16);

	}
	
	public Label(String ID, int x, int y, int width, int height, String text) {
		super(ID, x, y, width, height);
		this.text = text;
		this.colour = Color.WHITE;
		this.font = new Font("Calibri", Font.BOLD, 16);
		this.rec = new Rectangle(x, y - 16, width, height);

	}
	
	@Override
	public Rectangle getRectangle(){
		return rec;
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setFont(font);
		g.setColor(colour);
		g.drawString(text, getX(), getY());
		
	}

	@Override
	public void update(Paint paint) {
	
	}


}
