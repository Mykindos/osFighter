package net.betterpvp.osFighter.scheduler;

import net.betterpvp.osFighter.Fighter;

public interface Task {

	 void runTask(Fighter i);

	 boolean isComplete(Fighter i);

	 boolean validate(Fighter i);
}
