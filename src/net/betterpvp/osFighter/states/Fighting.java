package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.utilities.UtilWalking;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;

import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.WalkingEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Fighting extends ScriptState{


	private List<String> equipmentCache = new ArrayList<>();
	private ContainsNameFilter<Item> guthansFilter = new ContainsNameFilter<>("Guthan's helm", "Guthan's plate", "Guthan's chain", "Guthan's warspear");
	private NPC lastNPC = null;

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return i.getSessionData().getCurrentTargets().size() > 0; // Default state
	}


	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();

		if(data.getCombatAreaPositions().size() == 0 && data.getStartPosition().distance(i.myPosition()) > 50) {
			UtilWalking.webWalk(i, new Position[] {data.getStartPosition()}, null, true);
			return;
		}else {
			if(data.getCombatAreaPositions().size() == 1) {
				if(i.myPosition().distance(data.getCombatAreaPositions().get(0)) > 30) {
					UtilWalking.webWalk(i, new Position[] {data.getCombatAreaPositions().get(0)}, null, true);

				}
			}else {
				if(data.getCombatArea().getPositions().size() != 1) {

					if (!data.getCombatArea().contains(i.myPlayer())) {
						UtilWalking.webWalk(i, data.getCombatArea(), null, true);

					}
				}
			}

		}


		if(i.myPlayer().isUnderAttack() ||
				(i.myPlayer().getInteracting() != null && lastNPC != null && i.myPlayer().getInteracting() == lastNPC)) {
			// Maybe add a hover option when target is low health
			data.setLastAttacked(System.currentTimeMillis());



			if(data.isSafeSpotting()) {
				if (i.myPosition().getX() != data.getSafeSpot().getX() || i.myPosition().getY() != data.getSafeSpot().getY()) {
					walkSafeSpot(i);
					return;

				}
			}

			if(data.isOtherHealing()) {
				if(i.getSkills().getDynamic(Skill.HITPOINTS) < 100) {
					otherHealing(i);
				}
			}

			if(data.getSpecWeapon() != null && i.getCombat().getSpecialPercentage() >= data.getSpecWeapon().getCost()) {
				boolean skip = false;
				if(data.isUseSGS()) {
					if(i.myPlayer().getHealthPercent() > 90) {
						skip = true;
					}
				}

				if(!skip) {
					if(i.getEquipment().contains(data.getSpecWeapon().getFilter())) {

						i.getTabs().open(Tab.ATTACK);
						if(!i.getCombat().isSpecialActivated()) {
							i.getCombat().toggleSpecialAttack(true);
							new CustomSleep(() -> i.getCombat().isSpecialActivated(), 2000).sleep();
							return;
						}
					}else if(i.getInventory().contains(data.getSpecWeapon().getFilter())) {

						for(Item x : i.getEquipment().getItems()){
							if(x != null) {
								equipmentCache.add(x.getName());
							}
						}

						if(i.getInventory().interact("Wield", data.getSpecWeapon().getFilter())) {
							new CustomSleep(() -> i.getEquipment().contains(data.getSpecWeapon().getFilter()), 5000).sleep();
							UtilSleep.sleep(i, 100, 200);
							return;
						}
					}
				}
			}

			if(!i.getCombat().isSpecialActivated() ) {

				if(equipmentCache.size() > 0) {
					if(data.isUseGuthans()
							&& i.getEquipment().isWieldingWeapon(guthansFilter)
							&& (i.myPlayer().getHealthPercent() < 90
							|| i.getSkills().getDynamic(Skill.HITPOINTS) < data.getUseGuthansBelow())){
						return;
					}

					swapGear(i);
				}
			}


			return;
		}


		NPC npc = i.getNpcs().closest(n -> { 
			if(data.getCurrentTargets().stream().anyMatch(nA -> n.getName().equalsIgnoreCase(nA.getName())
					&& n.getLevel() == nA.getLevel())) {
				if(!n.isUnderAttack() && n.getInteracting() == null && n.getHealthPercent() > 0) {
					if(i.getMap().canReach(n)) {

						return true;
					}
				}
			}



			return false;

		});

		if(!i.getSettings().isRunning()){
			if(i.getSettings().getRunEnergy() > 15){
				i.getSettings().setRunning(true);
			}
		}

		if(!data.isAFKMode()){
		if(npc != null) {
			UtilSleep.sleep(i, 100, 250);
			if (npc.interact("Attack")) {
				lastNPC = npc;
				new CustomSleep(() -> (i.myPlayer().getInteracting() != null && i.myPlayer().isUnderAttack())
						|| (npc.isUnderAttack() && i.myPlayer().getInteracting() != null && i.myPlayer().getInteracting() == npc
						&& npc.getInteracting() == i.myPlayer() && i.myPlayer().isUnderAttack()
						|| (i.myPlayer().getInteracting() != null && lastNPC != null && i.myPlayer().getInteracting() == lastNPC)), 5000).sleep();

				if (data.isSafeSpotting()) {
					if (i.myPosition().getX() != data.getSafeSpot().getX() || i.myPosition().getY() != data.getSafeSpot().getY()) {
						i.log("Walking to safespot");
						walkSafeSpot(i);
						return;

					}
				}

				UtilSleep.sleep(i, 250, 500);


			}
		}
		}

	}

	private void otherHealing(Fighter i) {
		SessionData data = i.getSessionData();

		if(i.myPlayer().getInteracting() != null) {
			if(data.isUseGuthans()) {
				if(i.getSkills().getDynamic(Skill.HITPOINTS) < data.getUseGuthansBelow()) {
					if(i.getInventory().contains(guthansFilter)){
						for(Item x : i.getEquipment().getItems()){
							if(x != null) {
								equipmentCache.add(x.getName());
							}
						}


						for(Item x : i.getInventory().getItems()) {
							if(x.getName().toLowerCase().contains("guthan's warspear")) {
								if(i.getInventory().interact("Wield", x.getName())) {
									new CustomSleep(() -> i.getEquipment().contains(x.getName()), 2000).sleep();
								}
							}else if(x.getName().toLowerCase().contains("guthan")){
								if(i.getInventory().interact("Wear", x.getName())) {
									new CustomSleep(() -> i.getEquipment().contains(x.getName()), 2000).sleep();
								}
							}
						}
					}
				}
			}
		}

	}



	private void swapGear(Fighter i) {
		SessionData data = i.getSessionData();

		if(data.isUseGuthans()) {
			if(i.myPlayer().getHealthPercent() < 80) {
				if(i.getEquipment().contains(guthansFilter)) {
					return;
				}
			}
		}


		for(String item : equipmentCache) {
			if(i.getInventory().contains(item)) {
				int slot = i.getInventory().getSlot(item);
				if(i.getInventory().interact(slot, "Wield", "Wear")) {
					UtilSleep.sleep(i, 100, 225);
				}
			}
		}

		equipmentCache.clear();
	}

	private void walkSafeSpot(Fighter i){
		SessionData data = i.getSessionData();
		WalkingEvent ev = new WalkingEvent(data.getSafeSpot());
		ev.setMinDistanceThreshold(0);
		ev.setMiniMapDistanceThreshold(0);

		i.execute(ev);
		//i.log(i.myPosition().getX() + " vs " + data.getStartTile().getX() + " - " + i.myPosition().getY() + " vs " + data.getStartTile().getY());

	}

}
