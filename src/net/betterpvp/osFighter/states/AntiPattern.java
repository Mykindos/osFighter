package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;
import net.betterpvp.osFighter.antiban.AntiBanManager;
import net.betterpvp.osFighter.utilities.UtilMath;

public class AntiPattern extends ScriptState{

	@Override
	public boolean validate(Fighter i) throws InterruptedException {
		return UtilMath.randDouble(0, 10000) > 9995 && i.myPlayer().isAnimating()
				|| System.currentTimeMillis() > i.getSessionData().getLastAntiban() 
				+ i.getSessionData().getAntibanGap();
	}

	@Override
	public void run(Fighter i) throws InterruptedException {
		AntiBan ab = AntiBanManager.getNextModule();
		if(ab != null){
			ab.execute();
		}
		
		i.getSessionData().setLastAntiban(System.currentTimeMillis());
		i.getSessionData().setAntibanGap();
		
	}


}
