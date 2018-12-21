package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.data.Bank;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilEntity;
import net.betterpvp.osFighter.utilities.UtilSleep;
import net.betterpvp.osFighter.utilities.UtilWalking;
import org.osbot.rs07.api.model.Item;

public class Banking extends ScriptState {



    @Override
    public boolean validate(Fighter i) throws InterruptedException {
        if (i.getSessionData().isBanking()) {

            if (i.getInventory().isFull() && i.getInventory().contains(i.getSessionData().getFoodToEat())) {
                return false;
            }

            return (i.getSessionData().shouldBankNow() || i.getInventory().isFull());
        }
        // TODO create proper banking conditions
        return i.getSessionData().shouldBankNow();

    }

    @Override
    public void run(Fighter i) throws InterruptedException {
        SessionData data = i.getSessionData();
        if (i.getBank().isOpen()) {

            if (data.isDepositingInventory() || data.isDepositInvOverride()) {
                i.getBank().depositAll();
                new CustomSleep(() -> i.getInventory().isEmpty(), 2000).sleep();
            }


            data.getWithdrawData().forEach(w -> {
                ContainsNameFilter<Item> filt = new ContainsNameFilter<>(w.getItemName());
                if (i.getBank().contains(filt)) {
                    if (i.getBank().withdraw(filt, w.getAmount())) {
                        new CustomSleep(() -> i.getInventory().contains(filt)
                                && i.getInventory().getAmount(filt) >= w.getAmount(), 1000).sleep();
                        UtilSleep.sleep(100, 250);
                    }
                }
            });

            if(data.isEatingFood()){
                if(!i.getInventory().contains(new ContainsNameFilter<>(data.getFoodToEat()))){

                    i.log("You are out of food! If this is an error, report it to Tom on OSBot!");
                    i.stop();
                }
            }


            data.setShouldBankNow(false);
            data.setDepositInvOverride(data.isDepositingInventory());

        } else {

            i.getPrayer().deactivateAll();
            Area closestBank =  data.getBank() == Bank.AUTO ? UtilWalking.getClosestBank(i.myPosition()) : data.getBank().getArea();
            if (closestBank == null) {
                i.log("null bank");
                return;
            }
            if (closestBank.contains(i.myPlayer())) {

                if(!data.isBanking()){
                    if(data.shouldBankNow()){
                        i.stop();
                        i.log("Script ran to bank and stopped.");
                    }
                }

                if (i.getDepositBox().isOpen()) i.getDepositBox().close();


                // Finds closest bank, NPC OR RS2Object
                Entity bank = UtilEntity.getEntity(i, "Bank");
                if (bank != null) {
                    if (bank.interact("Bank")) {
                        new CustomSleep(() -> i.getBank().isOpen(), 3000).sleep();

                    }
                }
            } else {
                UtilWalking.webWalk(i, closestBank, null, true);


                UtilSleep.sleep(200, 400);
            }


        }
    }

}
