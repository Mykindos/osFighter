package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.model.NPC;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;

public class Fighting extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return true; // Default state
	}

	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();

		
		if(i.myPlayer().isUnderAttack()) {
			// Maybe add a hover option when target is low health
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

}
