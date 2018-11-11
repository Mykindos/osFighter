package net.betterpvp.osFighter.utilities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.utility.Condition;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.Bank;

public class UtilWalking {

	public static boolean canWalkPath(Fighter i, List<Position> path){
		return path.stream().anyMatch(p -> p.isOnMiniMap(i.getBot()));
	}

	public static boolean webWalk(Fighter i, Area pos, BooleanSupplier breakCondition) throws InterruptedException{
		return webWalk(i, pos, breakCondition, false);
	}


	public static boolean webWalk(Fighter i, Area pos, BooleanSupplier breakCondition, boolean teleports) throws InterruptedException{
		WebWalkEvent e = new WebWalkEvent(pos);

		PathPreferenceProfile prof = new PathPreferenceProfile();
		prof.checkInventoryForItems(true);
		prof.checkEquipmentForItems(true);
		prof.setAllowCharters(true);
		prof.setAllowObstacles(true);
		prof.setAllowGliders(true);
		prof.setAllowTeleports(teleports);

		e.setPathPreferenceProfile(prof);

		if(breakCondition != null) {
			e.setBreakCondition(new Condition(){

				@Override
				public boolean evaluate() {
					return breakCondition.getAsBoolean();
				}


			});
		}
		i.execute(e);

		return e.hasFinished();
	}

	public static boolean webWalk(Fighter i,Position[] pos, BooleanSupplier breakCondition, boolean teleports) throws InterruptedException{
		WebWalkEvent e = new WebWalkEvent(pos);

		PathPreferenceProfile prof = new PathPreferenceProfile();
		prof.checkInventoryForItems(true);
		prof.checkEquipmentForItems(true);
		prof.setAllowCharters(true);
		prof.setAllowObstacles(true);
		prof.setAllowGliders(true);
		prof.setAllowTeleports(teleports);

		e.setPathPreferenceProfile(prof);

		if(breakCondition != null) {
			e.setBreakCondition(new Condition(){

				@Override
				public boolean evaluate() {
					return breakCondition.getAsBoolean();
				}


			});
		}
		i.execute(e);

		return e.hasFinished();
	}


	public static boolean webWalkBank(Fighter i, Position myPosition) throws InterruptedException {
		return webWalk(i, getClosestBank(myPosition), null, true);
	}
	
	public static Area getClosestBank(Position myPosition) throws InterruptedException {
		List<Area> banks = Arrays.asList(Bank.getBankArea());
		banks.sort(new Comparator<Area>() {

			@Override
			public int compare(Area a, Area b) {
				return myPosition.distance(a.getRandomPosition()) - myPosition.distance(b.getRandomPosition());
			}
			
		});
	



		return banks.get(0);
	}




}
