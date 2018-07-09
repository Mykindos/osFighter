package net.betterpvp.osFighter.utilities;

import java.util.concurrent.ThreadLocalRandom;

import org.osbot.rs07.api.model.Entity;

public class UtilMath {
	
	public static int getDistance(Entity a, Entity b){
		return a.getPosition().distance(b.getPosition());
	}
	
	public static double randDouble(double min, double max) {
		return ThreadLocalRandom.current().nextDouble(min, max +1);
	}
	
	public static int randInt(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max +1);
	}
	

}
