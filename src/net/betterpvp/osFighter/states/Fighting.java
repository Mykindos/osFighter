package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.managers.special.SpecialFactory;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.event.WalkingEvent;

@SuppressWarnings("unchecked")
public class Fighting extends ScriptState{


	private Item[] equipmentCache = null;
	private ContainsNameFilter<Item> guthansFilter = new ContainsNameFilter<>("Guthan's helm", "Guthan's plate", "Guthan's chain", "Guthan's warspear");

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return true; // Default state
	}


	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();

		if(data.getCombatAreaPositions().size() == 0 && data.getStartPosition().distance(i.myPosition()) > 30) {
			i.getWalking().webWalk(data.getStartPosition());
			return;
		}else {
			if(data.getCombatAreaPositions().size() == 1) {
				if(i.myPosition().distance(data.getCombatAreaPositions().get(0)) > 30) {
					i.getWalking().webWalk(data.getCombatAreaPositions().get(0));
				}
			}else {
				if(!data.getCombatArea().contains(i.myPlayer())) {
					i.getWalking().webWalk(data.getCombatArea());
				}
			}

		}


		if(i.myPlayer().isUnderAttack() ) {
			// Maybe add a hover option when target is low health

			if(data.isSafeSpotting()) {
				if (i.myPosition().getX() != data.getSafeSpot().getX() || i.myPosition().getY() != data.getSafeSpot().getY()) {
					WalkingEvent ev = new WalkingEvent(data.getSafeSpot());
					ev.setMinDistanceThreshold(0);
					ev.setMiniMapDistanceThreshold(0);

					i.execute(ev);
					//i.log(i.myPosition().getX() + " vs " + data.getStartTile().getX() + " - " + i.myPosition().getY() + " vs " + data.getStartTile().getY());
					return;

				}
			}

			if(data.isSafeSpotting()) {
				if(i.myPosition() != data.getSafeSpot()) {
					// TODO Move to safe spot
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
					if(i.myPlayer().getHealthPercent() > 80) {
						skip = true;
					}
				}

				if(!skip) {
					if(i.getEquipment().contains(data.getSpecWeapon().getItemName())) {


						if(!i.getCombat().isSpecialActivated()) {
							i.getCombat().toggleSpecialAttack(true);
							new CustomSleep(() -> i.getCombat().isSpecialActivated(), 2000).sleep();
							return;
						}
					}else if(i.getInventory().contains(data.getSpecWeapon().getItemName())) {
						equipmentCache = i.getEquipment().getItems();

						if(i.getInventory().interact("Equip", data.getSpecWeapon().getItemName())) {
							new CustomSleep(() -> i.getEquipment().contains(data.getSpecWeapon().getItemName()), 5000).sleep();
							UtilSleep.sleep(i, 100, 200);
						}
					}
				}
			}

			if(!i.getCombat().isSpecialActivated()) { 
				if(equipmentCache != null) {
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

		if(npc != null) {
			UtilSleep.sleep(i, 100, 250);
			if(npc.interact("Attack")) {
				new CustomSleep(() -> (i.myPlayer().getInteracting() != null && i.myPlayer().isUnderAttack()) 
						|| (npc.isUnderAttack() && npc.getInteracting() != null 
						&& npc.getInteracting() == i.myPlayer() && i.myPlayer().isUnderAttack()), 5000).sleep();
				UtilSleep.sleep(i, 250, 500);

				if(data.isSafeSpotting()) {
					if (i.myPosition().getX() != data.getSafeSpot().getX() || i.myPosition().getY() != data.getSafeSpot().getY()) {
						WalkingEvent ev = new WalkingEvent(data.getSafeSpot());
						ev.setMinDistanceThreshold(0);
						ev.setMiniMapDistanceThreshold(0);

						i.execute(ev);
						//i.log(i.myPosition().getX() + " vs " + data.getStartTile().getX() + " - " + i.myPosition().getY() + " vs " + data.getStartTile().getY());
						return;

					}
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
						equipmentCache = i.getEquipment().getItems();

						for(Item x : i.getInventory().getItems()) {
							if(x.getName().toLowerCase().contains("guthan")) {
								if(i.getInventory().interact("Wield", x.getName())) {
									new CustomSleep(() -> i.getEquipment().contains(x.getName()), 2000).sleep();
								}
							}
						}
					}
				}
			}
		}

	}


	private void specialAttack(Fighter i) {
		SessionData data = i.getSessionData();

		if(data.isUseSGS()) {
			if(i.myPlayer().getHealthPercent() < 80) {
				if(i.getInventory().contains("Saradomin godsword") || i.getEquipment().contains("Saradomin godsword")) {
					if(i.getCombat().getSpecialPercentage() >= 50) {
						SpecialFactory.addSpecial("Saradomin godsword", 50);
						return;
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

		if(data.isUseSGS()) {
			if(i.myPlayer().getHealthPercent() < 80) {
				if(i.getInventory().contains("Saradomin godsword") || i.getEquipment().contains("Saradomin godsword")) {
					if(i.getCombat().getSpecialPercentage() >= 50) {
						SpecialFactory.addSpecial("Saradomin godsword", 50);
						return;
					}
				}
			}
		}

		for(Item item : equipmentCache) {
			if(i.getInventory().contains(item.getName())) {
				if(i.getInventory().interact("Wield", item.getName())) {
					UtilSleep.sleep(i, 100, 225);
				}
			}
		}

		equipmentCache = null;
	}

}
