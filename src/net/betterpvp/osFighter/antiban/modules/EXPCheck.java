package net.betterpvp.osFighter.antiban.modules;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;
import net.betterpvp.osFighter.utilities.CustomSleep;

public class EXPCheck extends AntiBan{

	public EXPCheck(Fighter i) {
		super(i, "Check current EXP", 10.0);
	}



	@Override
	public void execute() throws InterruptedException {

		logAB();
		getInstance().getTabs().open(Tab.SKILLS);

		new CustomSleep(() -> getInstance().getTabs().getOpen().equals(Tab.SKILLS), 1000).sleep(); 
		
		Script.sleep(Script.random(300, 800));
		//getInstance().getSkills().hoverSkill(Skill.MINING);
		Script.sleep(Script.random(800, 1400));
		
		// SHIT ANTI BAN FOR PEOPLE THAT BELIEVE IN IT


		openInventory();

	}



}
