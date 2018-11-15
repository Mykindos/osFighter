package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.data.Bank;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.Potions;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilMath;
import net.betterpvp.osFighter.utilities.UtilWalking;
import org.osbot.rs07.listener.MessageListener;

public class EatDrink extends ScriptState implements MessageListener {

    private int deviateBy = 0;
    private boolean drinkAntifire = true;
    private ContainsNameFilter<Item> teleportFilter = new ContainsNameFilter<>("teleport");
    // 1575 Stamina (Check > 0)
    // 102 Superantipoison (Check < 0)
    // 115 extended antifire?
    // Your poison resistance is about to wear off
    // Antifire potion is about to wear off


    @Override
    public boolean validate(Fighter i) throws InterruptedException {

        SessionData data = i.getSessionData();

        if (i.getBank().isOpen()) {
            return false;
        }


        if (data.isEatingFood()) {
            if (i.getSkills().getDynamic(Skill.HITPOINTS) <
                    (deviateBy == 0 ? data.getHealthToEatBelow() : deviateBy)) {
                return true;
            }


        }


        if (data.isDrinkPotions()) {
            if (i.getCombat().isPoisoned() || i.getCombat().isDiseased()) {
                if (data.isDrinkAntidotePotions() || data.isDrinkAntipoisonPotions() || data.isDrinkAntivenomPotions()) {
                    return true;
                }
            }

            if (data.isDrinkAntifirePotions()) {
                if (drinkAntifire) {
                    return true;
                }
            }


            if (data.isDrinkAttackPotions()) {
                if (checkStat(i, Skill.ATTACK, Potions.ATTACK)) {
                    return true;
                }
            }

            if (data.isDrinkStrengthPotions()) {
                if (checkStat(i, Skill.STRENGTH, Potions.STRENGTH)) {
                    return true;
                }
            }

            if (data.isDrinkDefencePotions()) {
                if (checkStat(i, Skill.DEFENCE, Potions.DEFENCE)) {
                    return true;
                }
            }

            if (data.isDrinkCombatPotions()) {
                if (checkStat(i, Skill.ATTACK, Potions.COMBAT)) {
                    return true;
                }
            }

            if (data.isDrinkRangingPotions()) {
                if (checkStat(i, Skill.RANGED, Potions.RANGED)) {
                    return true;
                }
            }

            if (data.isDrinkMagicPotions()) {
                if (checkStat(i, Skill.MAGIC, Potions.MAGIC)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean checkStat(Fighter i, Skill skill, Potions pot) {
        if (i.getSkills().getDynamic(skill) < i.getSkills().getStatic(skill) + 5) {
            if (i.getInventory().contains(pot.getOptions())) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run(Fighter i) throws InterruptedException {


        SessionData data = i.getSessionData();
        if (data.getHealthDeviation() != 0) {
            deviateBy = data.getHealthToEatBelow()
                    + UtilMath.randInt(data.getHealthDeviation() * -1, data.getHealthDeviation());
            i.log("Eating at " + deviateBy + " health");
        }

        if (data.isEatingFood()) {

            if (i.getSkills().getDynamic(Skill.HITPOINTS) < (deviateBy == 0 ? data.getHealthToEatBelow() : deviateBy)) {

                int healthPct = i.myPlayer().getHealthPercent();
                if (i.getInventory().contains(data.getFoodToEat())) {

                    int slot = i.getInventory().getSlot(new ContainsNameFilter<>(data.getFoodToEat()));
                    if (i.getInventory().interact(slot, "Eat", "Drink")) {

                        new CustomSleep(() -> i.myPlayer().getHealthPercent() > healthPct, 2000).sleep();
                    }
                } else {
                    data.setShouldBankNow(true);
                    if (data.isBanking()) {
                        Area bank = data.getBank() == Bank.AUTO ? UtilWalking.getClosestBank(i.myPosition()) : data.getBank().getArea();
                        UtilWalking.webWalk(i, bank, null, true);


                    } else {
                        if (i.getInventory().contains(teleportFilter)) {
                            Position p = i.myPosition();
                            i.getInventory().interact("Break", teleportFilter);
                            new CustomSleep(() -> i.myPosition() != p, 10000).sleep();
                        }
                    }
                    // TODO Explore escape options / stopping script
                    /*
                     * Teleport
                     * Walk
                     *
                     */

                    return;
                }
            }
        }

        if (data.isDrinkPotions()) {
            if (i.getCombat().isPoisoned()) {
                if (data.isDrinkAntidotePotions() || data.isDrinkAntipoisonPotions() || data.isDrinkAntivenomPotions()) {
                    drinkPotion(i, Potions.ANTIPOISON.getOptions());
                    new CustomSleep(() -> !i.getCombat().isPoisoned(), 1000).sleep();

                }
            }

            if (i.getCombat().isDiseased()) {
                if (data.isDrinkAntidotePotions() || data.isDrinkAntivenomPotions()) {
                    drinkPotion(i, Potions.ANTIVENOM.getOptions());
                    new CustomSleep(() -> !i.getCombat().isDiseased(), 500).sleep();
                    return;
                }
            }

            if (data.isDrinkAntifirePotions()) {
                if (drinkAntifire) {
                    if (drinkPotion(i, Potions.ANTIFIRE.getOptions())) {
                        drinkAntifire = false;
                        UtilSleep.sleep(i, 250, 500);
                    }
                }
            }


            if (data.isDrinkAttackPotions()) {
                if (checkStat(i, Skill.ATTACK, Potions.ATTACK)) {
                    drinkPotion(i, Potions.ATTACK.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.ATTACK, Potions.ATTACK), 500).sleep();
                }
            }

            if (data.isDrinkStrengthPotions()) {
                if (checkStat(i, Skill.STRENGTH, Potions.STRENGTH)) {
                    drinkPotion(i, Potions.STRENGTH.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.STRENGTH, Potions.STRENGTH), 500).sleep();
                }
            }

            if (data.isDrinkDefencePotions()) {
                if (checkStat(i, Skill.DEFENCE, Potions.DEFENCE)) {
                    drinkPotion(i, Potions.DEFENCE.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.DEFENCE, Potions.DEFENCE), 500).sleep();
                }
            }

            if (data.isDrinkCombatPotions()) {
                if (checkStat(i, Skill.ATTACK, Potions.COMBAT)) {
                    drinkPotion(i, Potions.COMBAT.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.ATTACK, Potions.COMBAT), 500).sleep();
                }
            }

            if (data.isDrinkRangingPotions()) {
                if (checkStat(i, Skill.RANGED, Potions.RANGED)) {
                    drinkPotion(i, Potions.ATTACK.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.RANGED, Potions.RANGED), 500).sleep();
                }
            }

            if (data.isDrinkMagicPotions()) {
                if (checkStat(i, Skill.MAGIC, Potions.MAGIC)) {
                    drinkPotion(i, Potions.MAGIC.getOptions());
                    new CustomSleep(() -> !checkStat(i, Skill.MAGIC, Potions.MAGIC), 500).sleep();
                }
            }
        }

    }

    private boolean drinkPotion(Fighter i, ContainsNameFilter<Item> filter) {

        if (i.getInventory().interact("Drink", filter)) {
            return true;
        }
        return false;
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        if (message.getMessage().toLowerCase().contains("antifire potion is about to wear off")) {
            drinkAntifire = true;
        }
    }
}
