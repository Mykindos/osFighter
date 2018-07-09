package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;

public class Banking extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return i.getInventory().isFull() ;
			
	}

	@Override
	public void run(Fighter i) throws InterruptedException {
		
	}

}
