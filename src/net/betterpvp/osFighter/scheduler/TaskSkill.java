package net.betterpvp.osFighter.scheduler;

public enum TaskSkill {

	ATTACK(0, "Chop", "Punch", "Flick", "Pound", "Lunge", "Spike"),
	STRENGTH(1, "Slash", "Kick", "Lash", "Hack", "Smash", "Pummel", "Swipe", "Impale", "Smash"),
	DEFENCE(3, "Block", "Deflect"),
	RANGED(1);

	private String[] actions;
	private int styleConfig;

	TaskSkill(int styleConfig, String... actions){
		this.actions = actions;
		this.styleConfig = styleConfig;
	}

	public String[] getActions(){
		return actions;
	}

	public int getStyleConfig(){
		return styleConfig;
	}
}
