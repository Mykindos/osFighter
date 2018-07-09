package net.betterpvp.osFighter.managers.paint.objects;

import java.awt.Point;

public class MousePathPoint extends Point {
	
	
	/*
	 * Credit to Swizzbeat and Enfilade for this snippet
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -5644074687861178854L;
	private long finishTime;
	private double lastingTime;
	private int alpha = 255;

	public MousePathPoint(int x, int y, int lastingTime) {
		super(x, y);
		this.lastingTime = lastingTime;
		finishTime = System.currentTimeMillis() + lastingTime;
	}

        //added by Swizzbeat
	public int getAlpha() {
		int newAlpha = ((int) ((finishTime - System.currentTimeMillis()) / (lastingTime / alpha)));
		if (newAlpha > 255)
			newAlpha = 255;
		if (newAlpha < 0)
			newAlpha = 0;
		return newAlpha;
	}

	public boolean isUp() {
		return System.currentTimeMillis() >= finishTime;
	}

}