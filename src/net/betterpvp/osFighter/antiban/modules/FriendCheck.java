package net.betterpvp.osFighter.antiban.modules;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;
import net.betterpvp.osFighter.utilities.CustomSleep;


public class FriendCheck extends AntiBan{

	public FriendCheck(Fighter i) {
		super(i, "Check Friends List", 1.0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws InterruptedException  {
		logAB();
		getInstance().getTabs().open(Tab.FRIENDS);
		
		new CustomSleep(() -> getInstance().getTabs().getOpen().equals(Tab.FRIENDS), 2000).sleep(); 
		
		Script.sleep(Script.random(450, 2000));
		
		openInventory();
		
	}

}
