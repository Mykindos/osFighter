package net.betterpvp.osFighter.scheduler;

public class TrainingTask implements Task {

	private TaskSkill skill;
	private int targetLevel;
	
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
}
