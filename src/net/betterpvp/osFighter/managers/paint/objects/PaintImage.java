package net.betterpvp.osFighter.managers.paint.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import net.betterpvp.osFighter.managers.Paint;
import net.betterpvp.osFighter.managers.paint.PaintObject;

public class PaintImage extends PaintObject{
	
	
	private Image image;

	public PaintImage(String ID, int x, int y, BufferedImage img) {
		super(ID, x, y, img.getWidth(), img.getHeight());
		this.image = img;
	}
	

	public Image getImage(){
		return image;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, getX(), getY(), null);
		
	}

	@Override
	public void update(Paint paint) {
		// TODO Auto-generated method stub
		
	}


}
