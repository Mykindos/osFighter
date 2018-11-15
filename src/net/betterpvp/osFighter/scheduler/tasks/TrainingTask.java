package net.betterpvp.osFighter.scheduler.tasks;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.scheduler.Task;
import net.betterpvp.osFighter.scheduler.TaskSkill;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilMath;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;

public class TrainingTask implements Task {

	private TaskSkill skill;
	private int targetLevel;
	private final int STYLE_CONFIG = 43;


	
	public TrainingTask(TaskSkill skill, int targetLevel) {
		this.skill = skill;
		this.targetLevel = targetLevel;
	}
	
	public TaskSkill getSkill() {
		return skill;
	}
	
	public int getTargetLevel() {
		return targetLevel;
	}

	@Override
	public void runTask(Fighter i) {


				i.getTabs().open(Tab.ATTACK);
				RS2Widget widg = i.getWidgets().containingText(getSkill().getActions()).get(0);
				if (widg != null && widg.isVisible()) {
					UtilSleep.sleep(i, 100, 250);
					widg.hover();
					i.getMouse().click(false);
					new CustomSleep(() -> i.getConfigs().get(STYLE_CONFIG) == getSkill().getStyleConfig(), 1000).sleep();
				}






	}

	@Override
	public boolean isComplete(Fighter i) {
		return i.getSkills().getStatic(Skill.valueOf(skill.name().toUpperCase())) >= getTargetLevel();
	}

	@Override
	public boolean validate(Fighter i) {
		return i.getConfigs().get(STYLE_CONFIG) != getSkill().getStyleConfig();
	}

	@Override
	public String toString(){
		return "Training " + skill.name() + " to " + getTargetLevel();
	}
}
