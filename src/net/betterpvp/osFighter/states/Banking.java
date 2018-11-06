package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilEntity;
import net.betterpvp.osFighter.utilities.UtilSleep;
import net.betterpvp.osFighter.utilities.UtilWalking;

public class Banking extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return i.getSessionData().isBanking() 
				&& (i.getInventory().isFull() || i.getSessionData().shouldBankNow()); 
		// TODO create proper banking conditions

	}

	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();
		if (i.getBank().isOpen()) {

			if(data.isDepositingInventory()) {
				i.getBank().depositAll();
				new CustomSleep(() -> i.getInventory().isEmpty(), 2000).sleep();
			}


			data.getWithdrawData().forEach(w -> {
				if(i.getBank().contains(w.getItemName())) {
					if(i.getBank().withdraw(w.getItemName(), w.getAmount())) {
						new CustomSleep(() -> i.getInventory().contains(w.getItemName()) 
								&& i.getInventory().getAmount(w.getItemName()) >= w.getAmount(), 1000).sleep();
						UtilSleep.sleep(i, 100, 250);
					}
				}
			});


			data.setShouldBankNow(false);

		} else {
			Area closestBank = UtilWalking.getClosestBank(i.myPosition());
			if(closestBank == null) {
				i.log("null bank");
				return;
			}
			if(closestBank.contains(i.myPlayer())) {


				if(i.getDepositBox().isOpen()) i.getDepositBox().close();


				// Finds closest bank, NPC OR RS2Object
				Entity bank = UtilEntity.getEntity(i, "Bank");
				if(bank != null){
					if(bank.interact("Bank")){
						new CustomSleep(() -> i.getBank().isOpen(), 3000).sleep();

					}
				}
			}else {
				i.getWalking().webWalk(closestBank);
				
				UtilSleep.sleep(i, 200, 400);
			}


		}
	}

}
