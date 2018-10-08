package net.betterpvp.osFighter.utilities;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.utility.Condition;

import net.betterpvp.osFighter.Fighter;

public class UtilWalking {

	public static boolean canWalkPath(Fighter i, List<Position> path){
		return path.stream().anyMatch(p -> p.isOnMiniMap(i.getBot()));
	}

	public static boolean webWalk(Area pos, BooleanSupplier breakCondition) throws InterruptedException{
		return webWalk(pos, breakCondition, false);
	}


	public static boolean webWalk(Area pos, BooleanSupplier breakCondition, boolean teleports) throws InterruptedException{
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
		e.execute();

		return e.hasFinished();
	}




}
