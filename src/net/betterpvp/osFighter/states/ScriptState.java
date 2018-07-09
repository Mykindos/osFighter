package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;

public abstract class ScriptState {
	
	public boolean execute(Fighter i) throws InterruptedException{
		if(validate(i)){
			
			run(i);
			return true;
		}
		return false;
	}

	public abstract boolean validate(Fighter i) throws InterruptedException;

	public abstract void run(Fighter i) throws InterruptedException;
		
}