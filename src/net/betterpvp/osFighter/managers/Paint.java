package net.betterpvp.osFighter.managers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.input.mouse.BotMouseListener;
import org.osbot.rs07.listener.MessageListener;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.managers.paint.PaintObject;
import net.betterpvp.osFighter.managers.paint.objects.MousePathPoint;
import net.betterpvp.osFighter.managers.paint.objects.PaintImage;



public class Paint extends BotMouseListener implements Painter, MessageListener{

	private Fighter i;
	public int mined = 0;
	private Point pos;
	
	private List<PaintObject> po = new ArrayList<>();
	private List<PaintObject> statLabels = new ArrayList<>();
	private int mX, mY;
	private long angle;
	private BasicStroke cursorStroke = new BasicStroke(2);
	private Color cursorColor = Color.CYAN;
	private AffineTransform oldTransform;
	private LinkedList<MousePathPoint> mousePath = new LinkedList<>();

	public Paint(Fighter i){
		this.i = i;
		String url = "http://mykindos.me/osFighter/";
		i.getSkills().getExperienceTracker().start(Skill.HITPOINTS);
		i.getSkills().getExperienceTracker().start(Skill.ATTACK);
		i.getSkills().getExperienceTracker().start(Skill.STRENGTH);
		i.getSkills().getExperienceTracker().start(Skill.DEFENCE);
		i.getSkills().getExperienceTracker().start(Skill.RANGED);
		i.getSkills().getExperienceTracker().start(Skill.MAGIC);

		try {
			addPaintObject(new PaintImage("Active Paint", 0, 309, ImageIO.read(new URL(url + "paint.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loadCommonLabels();



	}



	@Override
	public void onPaint(Graphics2D g) {

		if(i.hasStarted()){
		
			drawMouse(i, g);
			update();	
			draw(g);
		}

	}

	public void draw(Graphics2D g){
		for(PaintObject p : po){
			if(p.isEnabled()){
				p.draw(g);
			}
		}




	}

	public void update(){
		for(PaintObject p : po){
			if(p.isEnabled()){
				p.update(this);;
			}
		}
	}

	public void addPaintObject(PaintObject p){
		po.add(p);
	}

	public void addCommonPaintObject(PaintObject p){
		po.add(p);
		statLabels.add(p);
	}

	public PaintObject getPaintObject(String ID){
		for(PaintObject p : po){
			if(p.getID().equals(ID)){
				return p;
			}
		}

		return null;
	}

	public Point getMousePosition(){
		return this.pos;
	}

	public Fighter getInstance(){
		return this.i;
	}



	private String totalTime(int timer) {
		final int sec = timer / 1000, h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
		return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
		+ (s < 10 ? "0" + s : s);
	}

	


	public void loadCommonLabels(){
	
	
	}
	
	private void drawMouse(Fighter instance, Graphics2D g){
		oldTransform = g.getTransform();
		mX = instance.getMouse().getPosition().x;
		mY = instance.getMouse().getPosition().y;
		
		g.setColor(Color.CYAN);
		
		g.drawRect(mX -1, mY + 20, 1, 25);
		g.drawRect(mX -1, mY - 45, 1, 25);
		g.drawRect(mX + 20, mY -1, 25, 1);
		g.drawRect(mX - 45, mY -1, 25, 1);

		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		//MOUSE TRAIL
		while (!mousePath.isEmpty() && mousePath.peek().isUp())
			mousePath.remove();
		Point clientCursor = instance.getMouse().getPosition();
		MousePathPoint mpp = new MousePathPoint(clientCursor.x, clientCursor.y, 300);
		if (mousePath.isEmpty() || !mousePath.getLast().equals(mpp))
			mousePath.add(mpp);
		MousePathPoint lastPoint = null;
		for (MousePathPoint a : mousePath) {
			if (lastPoint != null) {
				g.setColor(new Color(255, 255, 255, a.getAlpha()));    //trail color
				g.drawLine(a.x, a.y, lastPoint.x, lastPoint.y);
			}
			lastPoint = a;
				}

		if (mX != -1) {
			g.setStroke(cursorStroke);
			g.setColor(cursorColor);
			g.drawLine(mX-3, mY-3, mX+2, mY+2);
			g.drawLine(mX-3, mY+2, mX+2, mY-3);

			g.rotate(Math.toRadians(angle+=6), mX, mY);

			g.draw(new Arc2D.Double(mX-12, mY-12, 24, 24, 330, 60, Arc2D.OPEN));
			g.draw(new Arc2D.Double(mX-12, mY-12, 24, 24, 151, 60, Arc2D.OPEN));

		        g.setTransform(oldTransform);
		}
	}



	@Override
	public void checkMouseEvent(MouseEvent e) {
		
		if(getPaintObject("Active Paint").getRectangle().contains(e.getPoint())){
		
			if(getPaintObject("Active Paint").isEnabled()){
				getPaintObject("Active Paint").setEnabled(false);
				for(PaintObject p : statLabels){
					p.setEnabled(false);
				}
			}else{
				getPaintObject("Active Paint").setEnabled(true);
				for(PaintObject p : statLabels){
					p.setEnabled(true);
					
				}
			}
			e.consume();


		}
		
		
	}



	@Override
	public void onMessage(Message arg0) throws InterruptedException {}


}
