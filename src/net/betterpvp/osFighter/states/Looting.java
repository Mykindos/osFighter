package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.ui.Spells;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.states.looting.LootCondition;
import net.betterpvp.osFighter.states.looting.LootedItem;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;

public class Looting extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return i.getGroundItems().getAll().stream().anyMatch(g -> {
			if(i.getSessionData().getLootableItems().stream().anyMatch(gA -> 
			gA.getItemName().equalsIgnoreCase(g.getName()))) {
				return true;
			}

			return false;
		});
	}

	private LootCondition lc = null;

	@SuppressWarnings("unchecked")
	@Override
	public void run(Fighter i) throws InterruptedException {

		lc = null;
		GroundItem gi = i.getGroundItems().closest(g -> {

			for(LootedItem li : i.getSessionData().getLootableItems()) {
				if(li.getItemName().equalsIgnoreCase(g.getName())) {
					if(li.getLootCondition() == LootCondition.NOTED && !g.getDefinition().isNoted()) continue; // Only pick up if noted
					lc = li.getLootCondition();
					return true;
				}
			}

			return false;
		});

		if(gi != null) {
			if(gi.interact("Pick-up")) {
				new CustomSleep(() ->  gi == null || !gi.exists(), 5000).sleep();
				UtilSleep.sleep(i, 200, 350);

				// Action after looting
				if(i.getInventory().contains(gi.getName())) {
					if(lc != LootCondition.NONE) {
						if(lc == LootCondition.LOW_ALCH || lc == LootCondition.HIGH_ALCH) {
							if(lc == LootCondition.LOW_ALCH) {
								if(i.getMagic().canCast(Spells.NormalSpells.LOW_LEVEL_ALCHEMY)) {
									if(i.getMagic().castSpell(Spells.NormalSpells.LOW_LEVEL_ALCHEMY)) {
										i.getInventory().interact(gi.getName(), "Cast");
										UtilSleep.sleep(i, 500, 750);
									}
								}
							}else if(lc == LootCondition.HIGH_ALCH) {
								if(i.getMagic().canCast(Spells.NormalSpells.HIGH_LEVEL_ALCHEMY)) {
									if(i.getMagic().castSpell(Spells.NormalSpells.HIGH_LEVEL_ALCHEMY)) {
										i.getInventory().interact(gi.getName(), "Cast");
										UtilSleep.sleep(i, 500, 750);
									}
								}
							}
						}

					}
				}
			}
		}

	}

}
