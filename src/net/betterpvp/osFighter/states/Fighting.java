package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.managers.special.SpecialFactory;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;

public class Fighting extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return true; // Default state
	}

	private Item[] equipmentCache = null;

	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();


		if(i.myPlayer().isUnderAttack()) {
			// Maybe add a hover option when target is low health

			if(data.isOtherHealing()) {
				if(i.getSkills().getDynamic(Skill.HITPOINTS) < 100) {
					otherHealing(i);
				}
			}

			if(data.getSpecWeapon() != null && i.getCombat().getSpecialPercentage() >= 25) {
				boolean skip = false;
				if(data.isUseSGS()) {
					if(i.myPlayer().getHealthPercent() > 80) {
						skip = true;
					}
				}

				if(!skip) {
					if(i.getEquipment().contains(data.getSpecWeapon())) {

						// TODO find out if you have enough spec percentage
						if(!i.getCombat().isSpecialActivated()) {
							i.getCombat().toggleSpecialAttack(true);
							new CustomSleep(() -> i.getCombat().isSpecialActivated(), 2000).sleep();
							return;
						}
					}else if(i.getInventory().contains(data.getSpecWeapon())) {
						equipmentCache = i.getEquipment().getItems();

						if(i.getInventory().interact("Equip", data.getSpecWeapon())) {
							new CustomSleep(() -> i.getEquipment().contains(data.getSpecWeapon()), 5000).sleep();
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

		@SuppressWarnings("unchecked")
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
			if(npc.interact("Attack")) {
				new CustomSleep(() -> i.myPlayer().getInteracting() != null && i.myPlayer().isUnderAttack(), 5000).sleep();
				UtilSleep.sleep(i, 250, 500);
			}
		}

	}

	private void otherHealing(Fighter i) {
		SessionData data = i.getSessionData();

		if(i.myPlayer().getInteracting() != null) {
			if(data.isUseGuthans()) {
				if(i.getSkills().getDynamic(Skill.HITPOINTS) < data.getUseGuthansBelow()) {

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
				if(i.getInventory().interact("Equip", item.getName())) {
					UtilSleep.sleep(i, 100, 225);
				}
			}
		}

		equipmentCache = null;
	}

}
