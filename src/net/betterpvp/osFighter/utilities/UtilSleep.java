package net.betterpvp.osFighter.utilities;

import org.osbot.rs07.script.Script;

import net.betterpvp.osFighter.Fighter;

public class UtilSleep {
	
	/**
	 * 
	 * @param i Script instance to access SessionData
	 * @param a First integer, the minimum
	 * @param b Second integer, the maximum
	 */
	public static void sleep(Fighter i, int min, int max){
	
		int random = Script.random(min, max);
		
		
		try {
			Script.sleep(random);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
