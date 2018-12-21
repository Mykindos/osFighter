package net.betterpvp.osFighter.scheduler;

public enum TaskSkill {

	ATTACK(0,0 , "Chop", "Punch", "Flick", "Pound", "Lunge", "Spike"),
	STRENGTH(1,2, "Slash","Lunge", "Kick", "Lash", "Hack", "Smash", "Pummel", "Swipe", "Impale", "Smash"),
	DEFENCE(3,3, "Block", "Deflect"),
	RANGED(1,1, "Rapid");

	private String[] actions;
	private int styleConfig;
	private int altConfig;

	TaskSkill(int styleConfig, int altConfig, String... actions){
		this.actions = actions;
		this.styleConfig = styleConfig;
		this.altConfig = altConfig;
	}

	public String[] getActions(){
		return actions;
	}

	public int getStyleConfig(){
		return styleConfig;
	}

	public int getAltConfig() {
		return altConfig;
	}
}
