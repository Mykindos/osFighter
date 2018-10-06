package net.betterpvp.osFighter.data;

public enum Potions {

	ATTACK("Attack", "Super Attack"),
	STRENGTH("Strength", "Super Strength"),
	DEFENCE("Defence", "Super Defence"),
	COMBAT("Combat", "Super Combat"),
	RANGED("Ranging"),
	MAGIC("Magic");
	
	String[] options;
	private Potions(String... options) {
		this.options = options;
	}
	
	public String[] getOptions() {
		return options;
	}
}
