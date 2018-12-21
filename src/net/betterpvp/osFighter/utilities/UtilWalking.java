package net.betterpvp.osFighter.utilities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BooleanSupplier;

import net.betterpvp.osFighter.data.BankOverrides;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.utility.Condition;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.Bank;

public class UtilWalking {
	private final static Area STRONGHOLD = new Area(1850, 5172, 2408, 5329);

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

		if(STRONGHOLD.contains(i.myPosition()) || (e.getDestination() != null && STRONGHOLD.contains(e.getDestination()))){
			e.setAsync();

			i.log("Walking in stronghold");
			i.execute(e);
			long start = System.currentTimeMillis();
			while(!e.hasFinished() || !e.hasFailed()){

				if(i.getDialogues().isPendingContinuation() || i.getDialogues().isPendingOption()){
					e.setFailed();
					e.interrupt();
					i.log("Cancelled web");
					break;
				}

				if(UtilTime.elapsed(start, 7000)){
					e.setFailed();
					e.interrupt();
					break;
				}



				i.sleep(100);
			}

			return e.hasFinished();

		}

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


		if(STRONGHOLD.contains(i.myPosition()) || (e.getDestination() != null && STRONGHOLD.contains(e.getDestination()))){
			e.setAsync();

			i.log("Walking in stronghold");
			i.execute(e);
			while(!e.hasFinished() || !e.hasFailed()){
				i.log("Thread stall");
				if(i.getDialogues().isPendingContinuation() || i.getDialogues().isPendingOption()){
					e.setFailed();
					e.interrupt();
					i.log("Cancelled web");
					break;
				}

				i.sleep(100);
			}

			return e.hasFinished();

		}

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


		for(Bank b : Bank.values()){
			if(b.getArea().contains(myPosition)){
				return b.getArea();
			}
		}

		for(BankOverrides b : BankOverrides.values()){
			if(b.getArea().contains(myPosition)){
				return b.getBank().getArea();
			}
		}


		List<Area> banks = Arrays.asList(Bank.getBankArea());
		banks.sort(Comparator.comparingInt(a -> myPosition.distance(a.getRandomPosition())));
	



		return banks.get(0);
	}

	public static Area getArea(Position p, int size){

		return new Area(p.translate(-size, -size), p.translate(size, size));
	}




}
