package net.betterpvp.osFighter.managers.special;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// Queue up special attacks from anywhere
public class SpecialFactory {
	
	private static Queue<Special> specialAttacks = new ConcurrentLinkedQueue<>();
	
	public static Queue<Special> getSpecialAttacks(){
		return specialAttacks;
	}

	public static void addSpecial(String item, int cost) {
		specialAttacks.add(new Special(item, cost));
	}
}
