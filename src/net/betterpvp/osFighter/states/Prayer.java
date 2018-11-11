package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.Prayers;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.PrayerButton;
import org.osbot.rs07.api.ui.Skill;

public class Prayer extends ScriptState {

    private ContainsNameFilter<Item> prayerPots = new ContainsNameFilter<Item>("prayer", "restore");

    @Override
    public boolean validate(Fighter i) throws InterruptedException {
        SessionData data = i.getSessionData();

        if (data.isUsePrayer()) {
            if((data.getCombatAreaPositions().size() > 0 && data.getCombatArea().contains(i.myPosition()))
                    || (data.getStartPosition().distance(i.myPlayer()) < 30 && data.getCombatAreaPositions().size() == 0)) {


                if (i.getSkills().getDynamic(Skill.PRAYER) < data.getDrinkBelowPrayer()) {
                if(!i.getInventory().contains(prayerPots) && i.getSkills().getDynamic(Skill.PRAYER) == 0
                        && !data.isBankWhenNoPrayerSupplies()){
                    return false;
                }
                return true;
            }


            Prayers offensivePrayer = data.getOffensivePrayer();
            if (offensivePrayer != Prayers.NONE && !i.prayer.isActivated(offensivePrayer.getButton())) {
                return true;
            }

            Prayers defensivePrayer = data.getDefensivePrayer();
            if (defensivePrayer != Prayers.NONE && !i.prayer.isActivated(defensivePrayer.getButton())) {
                return true;
            }

            Prayers protectionPrayer = data.getProtectionPrayer();
            if (protectionPrayer != Prayers.NONE && !i.prayer.isActivated(protectionPrayer.getButton())) {
                return true;
            }

            Prayers utilityPrayer = data.getUtilityPrayer();
            if (utilityPrayer != Prayers.NONE && !i.prayer.isActivated(utilityPrayer.getButton())) {
                return true;
            }

            Prayers healingPrayer = data.getHealingPrayer();
            if (healingPrayer != Prayers.NONE && !i.prayer.isActivated(healingPrayer.getButton())) {
                return true;
            }
            }

        }


        return false;
    }

    @Override
    public void run(Fighter i) throws InterruptedException {
        SessionData data = i.getSessionData();

        int points = i.getSkills().getDynamic(Skill.PRAYER);
        if (points < data.getDrinkBelowPrayer()) {

            if(data.isBanking()) {
                if (data.isBankWhenNoPrayerSupplies()) {
                    // Bank if we have no supplies to restore prayer
                    if (!i.getInventory().contains(prayerPots)) {
                        i.getSessionData().setShouldBankNow(true);
                        i.getPrayer().deactivateAll();
                        return;
                    }
                }
            }


            if(i.getInventory().contains(prayerPots)) {
                if(i.getBank().isOpen()){
                    i.getBank().close();
                    new CustomSleep(() -> !i.getBank().isOpen(), 1000).sleep();
                }
                if (i.getInventory().interact("Drink", prayerPots)) {
                    new CustomSleep(() -> i.getSkills().getDynamic(Skill.PRAYER) > points, 5000).sleep();
                    UtilSleep.sleep(i, 100, 250);
                }
            }else{
                return;
            }


        }

        Prayers offensivePrayer = data.getOffensivePrayer();
        if (offensivePrayer != Prayers.NONE && !i.prayer.isActivated(offensivePrayer.getButton())) {
           activatePrayer(i, offensivePrayer.getButton());
        }

        Prayers defensivePrayer = data.getDefensivePrayer();
        if (defensivePrayer != Prayers.NONE && !i.prayer.isActivated(defensivePrayer.getButton())) {
            activatePrayer(i, defensivePrayer.getButton());
        }

        Prayers protectionPrayer = data.getProtectionPrayer();
        if (protectionPrayer != Prayers.NONE && !i.prayer.isActivated(protectionPrayer.getButton())) {
            activatePrayer(i, protectionPrayer.getButton());
        }

        Prayers utilityPrayer = data.getUtilityPrayer();
        if (utilityPrayer != Prayers.NONE && !i.prayer.isActivated(utilityPrayer.getButton())) {
            activatePrayer(i, utilityPrayer.getButton());
        }

        Prayers healingPrayer = data.getHealingPrayer();
        if (healingPrayer != Prayers.NONE && !i.prayer.isActivated(healingPrayer.getButton())) {
            activatePrayer(i, healingPrayer.getButton());
        }



    }

    private boolean activatePrayer(Fighter i, PrayerButton button){
        if (i.getPrayer().set(button, true)){
            new CustomSleep(() -> i.getPrayer().isActivated(button), 1000).sleep();
            UtilSleep.sleep(i, 200, 500);
            return true;
        }


        return false;
    }
}
