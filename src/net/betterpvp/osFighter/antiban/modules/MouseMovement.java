package net.betterpvp.osFighter.antiban.modules;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;

public class MouseMovement extends AntiBan{

	public MouseMovement(Fighter i) {
		super(i, "Move Mouse Randomly", 7.0);

	}

	@Override
	public void execute() throws InterruptedException {
		logAB();
		getInstance().getMouse().moveOutsideScreen();
		
	}

}
