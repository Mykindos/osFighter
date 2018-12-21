package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.utilities.UtilPrice;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Spells;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.states.looting.LootCondition;
import net.betterpvp.osFighter.states.looting.LootedItem;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;
import net.betterpvp.osFighter.utilities.UtilTime;

public class Looting extends ScriptState {


    private long lastExecute = System.currentTimeMillis();

    @SuppressWarnings("unchecked")
    @Override
    public boolean validate(Fighter i) throws InterruptedException {

        if (i.getSessionData().getLootableItems().isEmpty() && !i.getSessionData().isPickingUpByValue()) {
            return false;
        }

        if (UtilTime.elapsed(lastExecute, 1000)) { // Performance
            lastExecute = System.currentTimeMillis();

            GroundItem item = i.getGroundItems().closest(g -> {
                if (i.getMap().canReach(g)) {
                    if (i.getSessionData().getCombatAreaPositions().size() > 0 && !i.getSessionData().getCombatArea().contains(g)) {
                        return false;
                    }
                    if (i.getInventory().isFull()) {
                        if (i.getSessionData().getLootableItems().stream().anyMatch(gA ->
                                gA.getItemName().equalsIgnoreCase(g.getName()))) {
                            for (LootedItem li : i.getSessionData().getLootableItems()) {

                                if (i.getInventory().contains(li.getItemName()) && li.isReplaceable()) {
                                    if (!li.getItemName().equalsIgnoreCase(g.getName())) { // Stop dropping / picking up same item

                                        return true;
                                    }
                                }

                            }
                        }


                        return false;

                    }


                    if (i.getSessionData().getLootableItems().stream().anyMatch(gA ->
                            gA.getItemName().equalsIgnoreCase(g.getName()))) {

                        return true;
                    }

                    if(i.getSessionData().isPickingUpByValue()) {
                        if (UtilPrice.getPrice(g.getId()) >= i.getSessionData().getMinPricePickup()) {
                            return true;
                        }
                    }
                }

                return false;
            });


            if (item != null) {
                return true;
            }


        }


        return false;
    }

    private LootCondition lc = null;

    @SuppressWarnings("unchecked")
    @Override
    public void run(Fighter i) throws InterruptedException {

        lc = null;
        if (i.getInventory().isFull()) {
            for (LootedItem li : i.getSessionData().getLootableItems()) {
                if (i.getInventory().contains(li.getItemName())) {
                    i.getInventory().drop(li.getItemName());
                    break;
                }
            }

        }


        GroundItem gi = i.getGroundItems().closest(g -> {



            for (LootedItem li : i.getSessionData().getLootableItems()) {
                if (li.getItemName().equalsIgnoreCase(g.getName())) {
                    if (li.getLootCondition() == LootCondition.NOTED && !g.getDefinition().isNoted())
                        continue; // Only pick up if noted
                    lc = li.getLootCondition();
                    return true;
                }
            }

            if(i.getSessionData().isPickingUpByValue()){
                if(UtilPrice.getPrice(g.getId()) >= i.getSessionData().getMinPricePickup()){
                    return true;
                }
            }

            return false;
        });

        if (gi != null) {
            if (gi.interact("Take")) {
                new CustomSleep(() -> gi == null || !gi.exists(), 5000).sleep();
                UtilSleep.sleep(200, 350);

                if(i.getInventory().isFull()) {
                    for(Item x : i.getInventory().getItems()) {
                        if (x != null) {
                        if(i.getSessionData().getLootableItems().stream().anyMatch(l -> l.getItemName().equalsIgnoreCase(x.getName())
                                && l.getLootCondition() == LootCondition.BURY)) {

                                int slot = i.getInventory().getSlot(x);
                                i.getInventory().interact(slot, "Bury");
                                new CustomSleep(() -> i.getInventory().getItemInSlot(slot) == null, 1000).sleep();
                                UtilSleep.sleep(250, 350);
                            }
                        }
                    }
                }

                // Action after looting
                if (i.getInventory().contains(gi.getName())) {
                    if (lc != LootCondition.NONE) {

                        if (lc == LootCondition.LOW_ALCH || lc == LootCondition.HIGH_ALCH) {
                            if (lc == LootCondition.LOW_ALCH) {
                                if (i.getMagic().canCast(Spells.NormalSpells.LOW_LEVEL_ALCHEMY)) {
                                    if (i.getMagic().castSpell(Spells.NormalSpells.LOW_LEVEL_ALCHEMY)) {
                                        i.getInventory().getItem(gi.getName()).hover();
                                        i.getMouse().click(false);
                                        UtilSleep.sleep(500, 750);
                                    }
                                }
                            } else if (lc == LootCondition.HIGH_ALCH) {
                                if (i.getMagic().canCast(Spells.NormalSpells.HIGH_LEVEL_ALCHEMY)) {
                                    if (i.getMagic().castSpell(Spells.NormalSpells.HIGH_LEVEL_ALCHEMY)) {
                                        i.getInventory().getItem(gi.getName()).hover();
                                        i.getMouse().click(false);

                                        UtilSleep.sleep( 500, 750);
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
