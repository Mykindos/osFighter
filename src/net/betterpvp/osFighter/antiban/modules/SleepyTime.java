package net.betterpvp.osFighter.antiban.modules;

import org.osbot.rs07.script.Script;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;

public class SleepyTime extends AntiBan{

	public SleepyTime(Fighter i) {
		super(i, "Simulate AFK behaviour", 5.0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws InterruptedException {
		logAB();
		long time = Script.random(15000, 100000);
		getInstance().log("Sleeping for " + time / 1000 + " seconds");
		Script.sleep(time);
		getInstance().log("Sleep Finished, resuming...");
	}

}
